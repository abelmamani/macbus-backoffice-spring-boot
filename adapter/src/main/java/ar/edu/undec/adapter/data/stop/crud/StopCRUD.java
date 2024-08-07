package ar.edu.undec.adapter.data.stop.crud;

import ar.edu.undec.adapter.data.stop.models.StopNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StopCRUD extends Neo4jRepository<StopNode, Long> {
    boolean existsByName(String name);
    Optional<StopNode> findByName(String name);
}
