package ar.edu.undec.adapter.data.privilege.crud;

import ar.edu.undec.adapter.data.privilege.models.PrivilegeNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeCRUD extends Neo4jRepository <PrivilegeNode, String> {
    boolean existsByName(String email);
    Optional<PrivilegeNode> findByName(String name);
}