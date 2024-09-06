package ar.edu.undec.adapter.data.stopsequence.crud;

import ar.edu.undec.adapter.data.stopsequence.models.StopSequenceNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface StopSequenceCRUD extends Neo4jRepository<StopSequenceNode, String> {
    @Query("""
    MATCH (s:Stop)<-[:STOP_AT]-(seq:StopSequence)<-[:HAS_STOP]-(r:Route)
    WHERE s.name = $stopName AND r.long_name <> $routeName
    RETURN COUNT(seq) > 0
    """)
    boolean isStopUsedInOtherRoutes(String stopName, String routeName);
    @Query("MATCH (r:Route {long_name: $longName})-[:HAS_STOP]->(ss:StopSequence)-[:STOP_AT]->(s:Stop)" +
            "RETURN {arrivalTime: ss.arrival_time, distanceTraveled: ss.distance_traveled, headsign: ss.headsign, stop: {name: s.name, latitude: s.latitude, longitude: s.longitude}} as stopsequence")
    List<Map<String, Object>> findAllStopSequencesByRouteLongName(String longName);
}