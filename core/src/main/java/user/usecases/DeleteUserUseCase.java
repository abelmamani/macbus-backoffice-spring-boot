package user.usecases;

import audit.EntityStatus;
import user.exceptions.UserNotExistException;
import user.inputs.DeleteUserInput;
import user.models.User;
import user.outputs.DeleteUserRepository;

public class DeleteUserUseCase implements DeleteUserInput {
    private DeleteUserRepository deleteUserRepository;

    public DeleteUserUseCase(DeleteUserRepository deleteUserRepository) {
        this.deleteUserRepository = deleteUserRepository;
    }

    @Override
    public void deleteUser(String email) {
        User findUser = deleteUserRepository.findByEmail(email).orElseThrow(() -> new UserNotExistException("No se puede eliminar, el usuario " + email + " no existe."));
        User user = User.getInstance(findUser.getId(),
                findUser.getName(),
                findUser.getLastName(),
                findUser.getEmail(),
                findUser.getPassword(),
                findUser.getResetToken(),
                findUser.getTokenExpiryDate(),
                EntityStatus.INACTIVE,
                findUser.getRole());
        deleteUserRepository.update(user);
    }
}
