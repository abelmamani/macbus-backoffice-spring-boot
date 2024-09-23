package user.usecases;

import user.exceptions.UserAlreadyExistsException;
import user.exceptions.UserInvalidException;
import user.exceptions.UserNotExistException;
import user.inputs.UpdateUserInput;
import user.models.ERole;
import user.models.UpdateUserRequestModel;
import user.models.User;
import user.outputs.UpdateUserRepository;

public class UpdateUserUseCase implements UpdateUserInput {
    private UpdateUserRepository updateUserRepository;
    public UpdateUserUseCase(UpdateUserRepository updateUserRepository) {
        this.updateUserRepository = updateUserRepository;
    }

    @Override
    public void updateUser(String email, UpdateUserRequestModel updateUserRequestModel) {
        User findUser = updateUserRepository.findByEmail(email).orElseThrow(() -> new UserNotExistException("El usuario " + email +" no existe."));
        if(updateUserRepository.existsByEmail(updateUserRequestModel.getEmail()) && !findUser.getEmail().equals(updateUserRequestModel.getEmail())){
            throw new UserAlreadyExistsException("El usuario con email "+updateUserRequestModel.getEmail()+" ya existe, utilice otro.");
        }
        try {
            ERole role = ERole.valueOf(updateUserRequestModel.getRole());
        } catch (IllegalArgumentException e) {
            throw new UserInvalidException("El Rol " + updateUserRequestModel.getRole() +" no es valido.");
        }
        User user = User.getInstance(findUser.getId(),
                updateUserRequestModel.getName(),
                updateUserRequestModel.getLastName(),
                updateUserRequestModel.getEmail(),
                findUser.getPassword(),
                ERole.valueOf(updateUserRequestModel.getRole()),
                findUser.getResetToken(),
                findUser.getTokenExpiryDate());
        updateUserRepository.update(user);
    }
}
