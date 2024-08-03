package user.usecases;

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
        Optional<User> user = getUserRepository.findById(id);
        if(user.isEmpty())
            throw new UserNotExistException("El usuario con id "+ id+" no existe.");
        return user.get();
    }
}
