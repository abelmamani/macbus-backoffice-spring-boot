package ar.edu.undec.adapter.data.busservice.crud;

import ar.edu.undec.adapter.data.busservice.models.ServiceNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ServiceCRUD extends Neo4jRepository<ServiceNode, String> {
    boolean existsByName(String name);
    Optional<ServiceNode> findByName(String name);
    @Query("MATCH (sr:Service {name: $name}) " +
            "OPTIONAL MATCH (sr)-[rel:HAS_CALENDAR_DATE]->(cd:CalendarDate) " +
            "DETACH DELETE cd, sr")
    void deleteByName(String name);
    @Query("MATCH (s:Service) RETURN {name: s.name, startDate: s.start_date, endDate: s.end_date} as service")
    List<Map<String, Object>> findAllServices();
}
