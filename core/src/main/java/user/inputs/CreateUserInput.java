package user.inputs;

import user.models.CreateUserRequestModel;

public interface CreateUserInput {
    String createUser(CreateUserRequestModel createUserRequestModel);
}
