package user.outputs;

import user.models.User;

public interface UpdateUserRepository {
    boolean existsByEmail(String email);
    Long update(User user);
}
