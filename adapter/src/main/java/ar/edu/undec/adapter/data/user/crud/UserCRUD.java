package ar.edu.undec.adapter.data.user.crud;

import ar.edu.undec.adapter.data.user.models.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserCRUD extends Neo4jRepository <UserNode, Long> {
    boolean existsByEmail(String email);
    Optional<UserNode> findByEmail(String email);
    Optional<UserNode> findByResetToken(String resetToken);
}
