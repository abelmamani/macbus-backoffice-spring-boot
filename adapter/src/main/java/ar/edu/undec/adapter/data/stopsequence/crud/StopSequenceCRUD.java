package ar.edu.undec.adapter.data.stopsequence.crud;

import ar.edu.undec.adapter.data.stopsequence.models.StopSequenceNode;
import busroute.models.RouteProgressStatus;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface StopSequenceCRUD extends Neo4jRepository<StopSequenceNode, String> {
    boolean existsByStopName(String stopName);
    @Query("MATCH (r:Route {long_name: $route})-[:HAS_STOP]->(ss:StopSequence {arrival_time: $arrivalTime}) RETURN count(ss) > 0")
    boolean existsByRouteAndArrivalTime(String route, String arrivalTime);

    @Query("MATCH (r:Route {long_name: $longName})-[:HAS_STOP]->(ss:StopSequence) " +
            "RETURN COUNT(ss) AS stopSequnceCount")
    int countStopSequencesByRoute(String longName);
    @Query("MATCH (r:Route {long_name: $longName})"+
            "OPTIONAL MATCH (ss:StopSequence {id: $sequenceId}) " +
            "CREATE (r)-[:HAS_STOP]->(ss) " +
            "SET r.progress_status = $routeStatus")
    void addStopSequence(String longName, String sequenceId, RouteProgressStatus routeStatus);
    @Query("MATCH (ss:StopSequence {id: $id}) DETACH DELETE ss")
    void deleteStopSequenceB(String id);
    @Query("MATCH (r:Route {long_name: $longName})-[:HAS_STOP]->(ss:StopSequence)-[:STOP_AT]->(s:Stop) " +
            "RETURN {id: ss.id, arrivalTime: ss.arrival_time, distanceTraveled: ss.distance_traveled, headsign: ss.headsign, stop: {id: s.id, name: s.name, latitude: s.latitude, longitude: s.longitude, assignedStatus: s.assigned_status, status: s.status}} as stopsequence "+
            "ORDER BY ss.arrival_time")
    List<Map<String, Object>> findAllStopSequencesByRouteLongName(String longName);
}