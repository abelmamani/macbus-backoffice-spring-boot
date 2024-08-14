package user.inputs;

import user.models.UpdateUserRequestModel;

public interface UpdateUserInput {
    void updateUser(String email, UpdateUserRequestModel updateUserRequestModel);
}
