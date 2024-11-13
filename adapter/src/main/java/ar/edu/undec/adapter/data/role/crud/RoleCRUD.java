package ar.edu.undec.adapter.data.role.crud;

import ar.edu.undec.adapter.data.role.models.RoleNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleCRUD extends Neo4jRepository <RoleNode, String> {
    boolean existsByName(String email);
    Optional<RoleNode> findByName(String name);
    @Query("MATCH (u:User)-[:HAS_ROLE]->(r:Role) WHERE r.id = $id RETURN COUNT(u) > 0")
    boolean isUsedByUsers(String id);
}