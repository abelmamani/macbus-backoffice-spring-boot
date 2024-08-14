package user.usecases;

import user.exceptions.UserNotExistException;
import user.inputs.GetUserInput;
import user.models.User;
import user.outputs.GetUserRepository;

public class GetUserUseCase implements GetUserInput {
    private GetUserRepository getUserRepository;

    public GetUserUseCase(GetUserRepository getUserRepository) {
        this.getUserRepository = getUserRepository;
    }

    @Override
    public User getUser(String email) {
        return getUserRepository.findByEmail(email).orElseThrow(() -> new UserNotExistException("El usuario "+ email +" no existe."));
    }
}
