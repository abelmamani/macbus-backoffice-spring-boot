package conductor.inputs;

import user.models.UpdateUserRequestModel;

public interface UpdateConductorInput {
    void updateConductor(String email, UpdateUserRequestModel updateUserRequestModel);
}
