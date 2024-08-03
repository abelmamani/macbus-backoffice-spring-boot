package user.inputs;

import user.models.User;

import java.util.Collection;
public interface GetUsersInput {
    Collection<User> getAllUsers();
}
