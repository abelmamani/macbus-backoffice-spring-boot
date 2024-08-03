package user.inputs;

import user.models.User;

public interface GetUserInput {
    User getUser(Long id);
}
