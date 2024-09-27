package ar.edu.undec.adapter.data.busroute.crud;

import ar.edu.undec.adapter.data.busroute.models.RouteNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import busroute.models.RouteGeneralInfoResponseModel;
import busroute.models.RouteStatus;
import report.models.RouteStatusCountsResponseModel;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface RouteCRUD extends Neo4jRepository<RouteNode, String> {
    boolean existsByShortName(String shortName);
    boolean existsByLongName(String longName);
    Optional<RouteNode> findByLongName(String longName);
    @Query("MATCH (r:Route {long_name: $longName}) RETURN r {.short_name, .long_name, .description, .color, .text_color, .route_status}")
    RouteGeneralInfoResponseModel findByRouteLongName(String longName);
    @Query("MATCH (r:Route) RETURN {shortName: r.short_name, longName: r.long_name, description: r.description, color: r.color, textColor: r.text_color, routeStatus: r.route_status} as route")
    List<Map<String, Object>> findAllRoutesGeneralInfo();
    @Query("MATCH (r:Route {long_name: $longName}) RETURN r.route_status")
    Optional<RouteStatus> getRouteStatusByLongName(String longName);
    @Query("MATCH (r:Route {long_name: $longName}) " +
            "SET r.route_status = $routeStatus")
    void updateRouteStatus(String longName, RouteStatus routeStatus);
    @Query("MATCH (r:Route) WHERE r.short_name = $shortName AND r.long_name <> $longName RETURN COUNT(r) > 0")
    boolean existsByShortNameAndNotLongName(String shortName, String longName);
    @Query("MATCH (r:Route {long_name: $name}) SET r.short_name = $shortName, r.long_name = $longName, r.description = $description, r.color = $color, r.text_color = $textColor")
    void updateGeneralInfo(String name, String shortName, String longName, String description, String color, String textColor);
    @Query("MATCH (r:Route {long_name: $longName}) DELETE r")
    void deleteByLongName(String longName);
    @Query("MATCH (r:Route {long_name: $longName}) " +
            "OPTIONAL MATCH (r)-[rel:HAS_SHAPE]->(s:Shape) " +
            "DETACH DELETE s, r")
    void deleteRouteAndShapes(String longName);
    @Query("MATCH (r:Route) " +
            "RETURN COUNT(r) AS total, " +
            "COUNT(CASE WHEN r.route_status = 'EMPTY' THEN 1 END) AS empty, " +
            "COUNT(CASE WHEN r.route_status = 'WITH_SHAPES' THEN 1 END) AS withShapes, " +
            "COUNT(CASE WHEN r.route_status = 'WITH_STOP' THEN 1 END) AS withStop, " +
            "COUNT(CASE WHEN r.route_status = 'WITH_STOPS' THEN 1 END) AS withStops, " +
            "COUNT(CASE WHEN r.route_status = 'WITH_TRIPS' THEN 1 END) AS withTrips")
    RouteStatusCountsResponseModel getRouteStatusCounts();
}