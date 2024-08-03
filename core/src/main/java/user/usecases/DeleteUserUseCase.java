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
    public void deleteUser(Long id) {
        if(!deleteUserRepository.existsById(id)){
            throw new UserNotExistException("No se puede eliminar, el usuario con id " + id + " no existe.");
        }
        deleteUserRepository.deleteById(id);
    }
}
