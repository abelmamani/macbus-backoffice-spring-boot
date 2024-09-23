package ar.edu.undec.adapter.data.user.crud;

import ar.edu.undec.adapter.data.user.models.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserCRUD extends Neo4jRepository <UserNode, String> {
    boolean existsByEmail(String email);
    Optional<UserNode> findByEmail(String email);
    Optional<UserNode> findByResetToken(String resetToken);
    void deleteByEmail(String email);
    @Query("MATCH (u:User {role: 'ROLE_CONDUCTOR'}) RETURN {id: u.id, name: u.name, lastName: u.last_name, email: u.email} AS conductor")
    List<Map<String, Object>> findConductors();
    @Query("MATCH (u:User) RETURN {id: u.id, name: u.name, lastName: u.last_name, email: u.email, role: u.role} AS conductor")
    List<Map<String, Object>> findAllUsers();
}
