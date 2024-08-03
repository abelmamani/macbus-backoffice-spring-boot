package user.inputs;

import user.models.UpdateUserRequestModel;

public interface UpdateUserInput {
    Long updateUser(UpdateUserRequestModel updateUserRequestModel);
}
