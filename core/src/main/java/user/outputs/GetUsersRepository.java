package user.outputs;

import user.models.User;

import java.util.Collection;

public interface GetUsersRepository {
    Collection<User> findAll();
}
