package user.usecases;

import user.exceptions.UserNotExistException;
import user.inputs.DeleteUserInput;
import user.outputs.DeleteUserRepository;

public class DeleteUserUseCase implements DeleteUserInput {
    private DeleteUserRepository deleteUserRepository;

    public DeleteUserUseCase(DeleteUserRepository deleteUserRepository) {
        this.deleteUserRepository = deleteUserRepository;
    }

    @Override
    public void deleteUser(String email) {
        if(!deleteUserRepository.existsByEmail(email))
            throw new UserNotExistException("No se puede eliminar, el usuario " + email + " no existe.");
        deleteUserRepository.deleteByEamil(email);
    }
}
