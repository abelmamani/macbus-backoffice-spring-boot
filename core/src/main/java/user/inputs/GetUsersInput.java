package user.inputs;

import user.models.UserResponseModel;
import java.util.Collection;
public interface GetUsersInput {
    Collection<UserResponseModel> getAllUsers();
}
