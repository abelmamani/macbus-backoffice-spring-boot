package ar.edu.undec.adapter.data.route.crud;

import ar.edu.undec.adapter.data.route.models.RouteNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import route.models.RouteGeneralInfoResponseModel;
import route.models.RouteStatus;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RouteCRUD extends Neo4jRepository<RouteNode, String> {
    boolean existsByShortName(String shortName);
    boolean existsByLongName(String longName);
    Optional<RouteNode> findByLongName(String longName);
    @Query("MATCH (r:Route) RETURN r {.short_name, .long_name, .description, .color, .text_color, .route_status}")
    List<RouteGeneralInfoResponseModel> findAllRoutesGeneralInfo();
    @Query("MATCH (r:Route {long_name: $longName}) RETURN r.route_status")
    Optional<RouteStatus> getRouteStatusByLongName(String longName);
    @Query("MATCH (r:Route {long_name: $longName}) DELETE r")
    void deleteByLongName(String longName);
    @Query("MATCH (r:Route {long_name: $longName}) " +
            "OPTIONAL MATCH (r)-[rel:HAS_SHAPE]->(s:Shape) " +
            "DELETE rel, s, r")
    void deleteRouteAndShapes(String longName);
    @Query("MATCH (r:Route)-[rel:HAS_SHAPE]->(s:Shape) WHERE r.long_name = $longName DELETE rel, s")
    void deleteShapesByLongName(String longName);
    @Query("MATCH (r:Route)-[rel1:HAS_STOP]->(ss:StopSequence)-[rel2:STOP_AT]->(s:Stop) " +
            "WHERE r.long_name = $longName AND ss.arrival_time = $arrivalTime " +
            "DELETE rel2, rel1, ss")
    void deleteStopSequenceByLongNameAndArrivalTime(String longName, LocalTime arrivalTime);

    @Query("MATCH (r:Route)-[ht:HAS_TRIP]->(t:Trip)<-[pof:PART_OF_TRIP]-(st:StopTime)-[lat:LOCATED_AT]->(s:Stop) " +
            "MATCH (t)-[:TRIP_AT]->(sr:Service {name: $serviceName}) " +
            "WHERE r.long_name = $longName AND t.departure_time = $departureTime " +
            "DETACH DELETE t, st")
    void deleteTripAndStopTimes(String longName, LocalTime departureTime, String serviceName);

}