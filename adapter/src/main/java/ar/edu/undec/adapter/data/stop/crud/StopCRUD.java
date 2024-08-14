package ar.edu.undec.adapter.data.stop.crud;

import ar.edu.undec.adapter.data.stop.models.StopNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import stop.models.StopStatus;
import java.util.Optional;

@Repository
public interface StopCRUD extends Neo4jRepository<StopNode, String> {
    boolean existsByName(String name);
    boolean existsByNameAndStatus(String name, StopStatus status);
    Optional<StopNode> findByName(String name);
    void deleteByName(String name);
}
