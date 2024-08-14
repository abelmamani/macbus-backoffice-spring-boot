package user.usecases;

import user.exceptions.UserAlreadyExistsException;
import user.exceptions.UserInvalidException;
import user.exceptions.UserNotExistException;
import user.inputs.GetUserInput;
import user.inputs.UpdateUserInput;
import user.models.ERole;
import user.models.UpdateUserRequestModel;
import user.models.User;
import user.outputs.UpdateUserRepository;

public class UpdateUserUseCase implements UpdateUserInput {
    private UpdateUserRepository updateUserRepository;
    private GetUserInput getUserInput;
    public UpdateUserUseCase(UpdateUserRepository updateUserRepository, GetUserInput getUserInput) {
        this.updateUserRepository = updateUserRepository;
        this.getUserInput = getUserInput;
    }

    @Override
    public Long updateUser(UpdateUserRequestModel updateUserRequestModel) {
        User findUser = getUserInput.getUser(updateUserRequestModel.getId());
        if(updateUserRepository.existsByEmail(updateUserRequestModel.getEmail()) && !findUser.getEmail().equals(updateUserRequestModel.getEmail())){
            throw new UserAlreadyExistsException("El usuario con email "+updateUserRequestModel.getEmail()+" ya existe, utilice otro.");
        }
        try {
            ERole role = ERole.valueOf(updateUserRequestModel.getRole());
        } catch (IllegalArgumentException e) {
            throw new UserInvalidException("El Rol " + updateUserRequestModel.getRole() +" no es valido.");
        }
        User user = User.getInstance(updateUserRequestModel.getId(),
                updateUserRequestModel.getName(),
                updateUserRequestModel.getLastName(),
                updateUserRequestModel.getEmail(),
                findUser.getPassword(),
                ERole.valueOf(updateUserRequestModel.getRole()),
                null,
                null);
        return updateUserRepository.update(user);
    }
}
