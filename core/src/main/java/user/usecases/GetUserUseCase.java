package user.usecases;

import stop.models.Stop;
import user.exceptions.UserNotExistException;
import user.inputs.GetUserInput;
import user.models.User;
import user.outputs.GetUserRepository;

import java.util.Optional;

public class GetUserUseCase implements GetUserInput {
    private GetUserRepository getUserRepository;

    public GetUserUseCase(GetUserRepository getUserRepository) {
        this.getUserRepository = getUserRepository;
    }

    @Override
    public User getUser(Long id) {
        return getUserRepository.findById(id).orElseThrow(() -> new UserNotExistException("El usuario con id "+ id+" no existe."));
    }
}
