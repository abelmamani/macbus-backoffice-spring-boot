package ar.edu.undec.adapter.data.trip.crud;
import ar.edu.undec.adapter.data.trip.models.TripNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import trip.models.TripResponseModel;

import java.util.List;
import java.util.Map;

@Repository
public interface TripCRUD extends Neo4jRepository<TripNode, String> {
    @Query("MATCH (r:Route)-[:HAS_TRIP]->(t:Trip)-[:TRIP_AT]->(s:Service) " +
            "WHERE r.long_name = $longName " +
            "RETURN {departureTime: t.departure_time, tripStatus: t.trip_status, service: s.name}")
    List<Map<String, Object>> findAllTripsByRouteLongName(String longName);
    @Query("MATCH (t:Trip)-[:TRIP_AT]->(s:Service) WHERE s.name = $serviceName RETURN count(t) > 0")
    boolean existsByServiceName(String serviceName);
}
