package user.outputs;

import user.models.User;

import java.util.Optional;

public interface GetUserRepository {
    Optional<User> findById(Long id);
}
