package conductor.inputs;
import user.models.CreateUserRequestModel;

public interface CreateConductorInput {
    void createConductor(CreateUserRequestModel createUserRequestModel);
}
