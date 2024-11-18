package ar.edu.undec.adapter.data.stop.crud;

import ar.edu.undec.adapter.data.stop.models.StopNode;
import audit.EntityStatus;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import report.models.StopStatusCountsResponseModel;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface StopCRUD extends Neo4jRepository<StopNode, String> {
    boolean existsByName(String name);
    Collection<StopNode> findAllByStatus(EntityStatus status);
    Optional<StopNode> findByNameAndStatus(String name, EntityStatus status);
    @Query("MATCH (s:Stop) " +
            "WHERE s.assigned_status IS NOT NULL "+
            "RETURN COUNT(s) AS total, " +
            "COUNT(CASE WHEN s.assigned_status = 'ASSIGNED' THEN 1 END) AS assigned, " +
            "COUNT(CASE WHEN s.assigned_status = 'UNASSIGNED' THEN 1 END) AS unassigned")
    StopStatusCountsResponseModel getStopStatusCounts();
}
