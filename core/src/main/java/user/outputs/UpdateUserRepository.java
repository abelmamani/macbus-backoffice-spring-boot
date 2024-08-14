package user.outputs;

import user.models.User;

import java.util.Optional;

public interface UpdateUserRepository {
    boolean existsByEmail(String email);
    Optional<User> findByEamil(String email);
    void update(User user);
}
