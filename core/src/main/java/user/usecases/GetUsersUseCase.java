package user.usecases;

import user.inputs.GetUsersInput;
import user.models.User;
import user.outputs.GetUsersRepository;

import java.util.Collection;

public class GetUsersUseCase implements GetUsersInput {
    private GetUsersRepository getUsersRepository;

    public GetUsersUseCase(GetUsersRepository getUsersRepository) {
        this.getUsersRepository = getUsersRepository;
    }

    @Override
    public Collection<User> getAllUsers() {
        return getUsersRepository.findAll();
    }
}
