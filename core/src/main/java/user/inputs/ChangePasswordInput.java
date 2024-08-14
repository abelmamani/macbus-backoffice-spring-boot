package user.inputs;

import user.models.ChangePasswordRequestModel;

public interface ChangePasswordInput {
    void changePassword(ChangePasswordRequestModel changePasswordRequestModel);
}
