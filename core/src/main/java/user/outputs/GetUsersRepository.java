package user.outputs;

import user.models.UserResponseModel;
import java.util.Collection;

public interface GetUsersRepository {
    Collection<UserResponseModel> findAll();
}
