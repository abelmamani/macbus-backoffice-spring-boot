package ar.edu.undec.adapter.data.trip.crud;
import ar.edu.undec.adapter.data.trip.models.TripNode;
import busroute.models.RouteStatus;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import report.models.TripStatusCountsResponseModel;
import java.util.List;
import java.util.Map;

@Repository
public interface TripCRUD extends Neo4jRepository<TripNode, String> {
    @Query("MATCH (t:Trip)-[:TRIP_AT]->(s:Service {name: $service}) RETURN count(t) > 0")
    boolean existsByServiceName(String service);

    @Query("MATCH (r:Route {long_name: $route})-[:HAS_TRIP]->(t:Trip {departure_time: $departureTime})-[:TRIP_AT]->(s:Service {name: $service}) RETURN count(t) > 0")
    boolean existsByRouteAndDepartureTimeAndService(String route, String departureTime, String service);
    @Query("MATCH (r:Route {long_name: $longName})-[:HAS_TRIP]->(t:Trip) " +
            "RETURN COUNT(t) AS tripCount")
    int countTripsByRoute(String longName);
    @Query("MATCH (r:Route {long_name: $longName}) " +
            "OPTIONAL MATCH (t:Trip {id: $tripId}) "+
            "CREATE (r)-[:HAS_TRIP]->(t) " +
            "SET r.route_status = $routeStatus")
    void addTrip(String longName, String tripId, RouteStatus routeStatus);
    @Query("MATCH (t:Trip {id: $tripId})<-[pof:PART_OF_TRIP]-(st:StopTime) DETACH DELETE t, st")
    void deleteTripAndStopTimes(String tripId);
    @Query("MATCH (r:Route {long_name: $longName})-[:HAS_TRIP]->(t:Trip)-[:TRIP_AT]->(s:Service) " +
            "RETURN {id: t.id, departureTime: t.departure_time, tripStatus: t.trip_status, service: s.name} AS trip")
    List<Map<String, Object>> findAllTripsByRouteLongName(String longName);
    @Query("MATCH (t:Trip) " +
            "RETURN COUNT(t) AS total, " +
            "COUNT(CASE WHEN t.trip_status = 'SCHEDULED' THEN 1 END) AS scheduled, " +
            "COUNT(CASE WHEN t.trip_status = 'RUNNING' THEN 1 END) AS running, " +
            "COUNT(CASE WHEN t.trip_status = 'COMPLETED' THEN 1 END) AS completed, " +
            "COUNT(CASE WHEN t.trip_status = 'CANCELLED' THEN 1 END) AS cancelled")
    TripStatusCountsResponseModel getTripStatusCounts();
}
