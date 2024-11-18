package user.outputs;

import user.models.User;
import java.util.Optional;

public interface DeleteUserRepository {
    Optional<User> findByEmail(String email);
    void update(User user);

    //boolean existsByEmail(String email);
    //void deleteByEmail(String email);
}
