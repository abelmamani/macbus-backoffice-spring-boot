package ar.edu.undec.adapter.data.tripupdate.crud;

import ar.edu.undec.adapter.data.tripupdate.models.TripUpdateNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface TripUpdateCRUD extends Neo4jRepository<TripUpdateNode, String> {
    @Query("MATCH (r:Route)-[:HAS_TRIP]->(t:Trip {trip_status: 'RUNNING'})-[:HAS_UPDATE]->(tu:TripUpdate) " +
            "RETURN {id: t.id, latitude: tu.latitude, longitude: tu.longitude, timestamp: toInteger(tu.timestamp), route: r.long_name} AS triUpdate")
    List<Map<String, Object>> findTripUpdates();
    @Query("MATCH (t:Trip {id: $id, trip_status: 'RUNNING'})-[:HAS_UPDATE]->(tu:TripUpdate) " +
            "WHERE tu.timestamp < datetime().epochMillis - (5 * 60 * 1000) " +
            "RETURN COUNT(tu) > 0")
    boolean isTripUpdateRunning(String id);
    @Query("MATCH (t:Trip {id: $id}) SET t.trip_status = 'SCHEDULED'")
    void stopTripUpdate(String id);
}
