package user.usecases;

import user.exceptions.UserNotExistException;
import user.inputs.ChangePasswordInput;
import user.models.ChangePasswordRequestModel;
import user.models.User;
import user.outputs.ChangePasswordRepository;

public class ChangePasswordUseCase implements ChangePasswordInput {
    private ChangePasswordRepository changePasswordRepository;

    public ChangePasswordUseCase(ChangePasswordRepository changePasswordRepository) {
        this.changePasswordRepository = changePasswordRepository;
    }

    @Override
    public void changePassword(ChangePasswordRequestModel changePasswordRequestModel) {
        User user = changePasswordRepository.findByEmail(changePasswordRequestModel.getEmail())
                .orElseThrow(() -> new UserNotExistException("El usuario con email "+changePasswordRequestModel.getEmail()+" no existe."));
        User updateUser = User.getInstance(user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                changePasswordRepository.encodePassword(changePasswordRequestModel.getPassword()),
                user.getResetToken(),
                user.getTokenExpiryDate(),
                user.getStatus(),
                user.getRole());
        changePasswordRepository.save(updateUser);
    }
}
