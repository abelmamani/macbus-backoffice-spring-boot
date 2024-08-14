package user.outputs;

import user.models.User;

import java.util.Optional;

public interface ChangePasswordRepository {
    Optional<User> findByEmail(String email);
    String encodePassword(String password);
    void save(User user);
}
