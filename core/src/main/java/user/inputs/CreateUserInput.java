package user.inputs;

import user.models.CreateUserRequestModel;

public interface CreateUserInput {
    Long createUser(CreateUserRequestModel createUserRequestModel);
}
