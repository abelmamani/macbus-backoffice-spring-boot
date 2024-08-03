package auth.outputs;

import user.models.User;

public interface LoginRepository {
    User findByEmail(String email);
    boolean authenticate(String email, String password);
    String generateToken(User user);
}
