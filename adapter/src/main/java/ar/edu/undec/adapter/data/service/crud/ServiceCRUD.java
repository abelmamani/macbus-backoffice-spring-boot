package ar.edu.undec.adapter.data.service.crud;

import ar.edu.undec.adapter.data.service.models.ServiceNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceCRUD extends Neo4jRepository<ServiceNode, Long> {
    boolean existsByName(String name);
    Optional<ServiceNode> findByName(String name);
}
