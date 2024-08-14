package user.outputs;

import user.models.User;

public interface CreateUserRepository {

    boolean existsByEmail(String email);
    String encodePassword(String password);
    String save(User user);
}
