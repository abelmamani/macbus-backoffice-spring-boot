package auth.outputs;

import user.models.User;

import java.util.Optional;

public interface RecoverPasswordRepository {
    Optional<User> findByEmail(String email);
    void sendPasswordResetEmail(String email, String resetToken);
    Optional<User> findByResetToken(String token);
    String encodePassword(String password);
    void save(User user);
}
