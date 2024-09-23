package ar.edu.undec.adapter.data.trip.crud;
import ar.edu.undec.adapter.data.trip.models.TripNode;
import busroute.models.RouteStatus;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface TripCRUD extends Neo4jRepository<TripNode, String> {
    @Query("MATCH (r:Route {long_name: $longName})-[:HAS_TRIP]->(t:Trip)-[:TRIP_AT]->(s:Service) " +
            "RETURN {departureTime: t.departure_time, tripStatus: t.trip_status, service: s.name} AS trip")
    List<Map<String, Object>> findAllTripsByRouteLongName(String longName);
    @Query("MATCH (t:Trip)-[:TRIP_AT]->(s:Service {name: $service}) RETURN count(t) > 0")
    boolean existsByServiceName(String service);

    @Query("MATCH (r:Route {long_name: $route})-[:HAS_TRIP]->(t:Trip {departure_time: $departureTime})-[:TRIP_AT]->(s:Service {name: $service}) RETURN count(t) > 0")
    boolean existsByRouteAndDepartureTimeAndService(String route, String departureTime, String service);
    @Query("MATCH (r:Route {long_name: $longName})" +
            "MATCH (t:Trip {id: $tripId})" +
            "MERGE (r)-[:HAS_TRIP]->(t)" +
            "SET r.route_status = $routeStatus")
    void addTrip(String longName, String tripId, RouteStatus routeStatus);

}
