package ar.edu.undec.adapter.data.stop.crud;

import ar.edu.undec.adapter.data.stop.models.StopNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import report.models.StopStatusCountsResponseModel;
import stop.models.StopStatus;
import java.util.Optional;

@Repository
public interface StopCRUD extends Neo4jRepository<StopNode, String> {
    boolean existsByName(String name);
    boolean existsByNameAndStatus(String name, StopStatus status);
    Optional<StopNode> findByName(String name);
    void deleteByName(String name);
    @Query("MATCH (s:Stop) " +
            "WHERE s.status IS NOT NULL "+
            "RETURN COUNT(s) AS total, " +
            "COUNT(CASE WHEN s.status = 'ASSIGNED' THEN 1 END) AS assigned, " +
            "COUNT(CASE WHEN s.status = 'UNASSIGNED' THEN 1 END) AS unassigned")
    StopStatusCountsResponseModel getStopStatusCounts();
}
