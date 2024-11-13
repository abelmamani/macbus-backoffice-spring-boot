package user.usecases;

import role.exceptions.RoleException;
import role.models.Role;
import role.outputs.RoleRepository;
import user.exceptions.UserAlreadyExistsException;
import user.exceptions.UserNotExistException;
import user.inputs.UpdateUserInput;
import user.models.UpdateUserRequestModel;
import user.models.User;
import user.outputs.UpdateUserRepository;

public class UpdateUserUseCase implements UpdateUserInput {
    private UpdateUserRepository updateUserRepository;
    private RoleRepository roleRepository;

    public UpdateUserUseCase(UpdateUserRepository updateUserRepository, RoleRepository roleRepository) {
        this.updateUserRepository = updateUserRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void updateUser(String email, UpdateUserRequestModel updateUserRequestModel) {
        User findUser = updateUserRepository.findByEmail(email).orElseThrow(() -> new UserNotExistException("El usuario " + email +" no existe."));
        if(updateUserRepository.existsByEmail(updateUserRequestModel.getEmail()) && !findUser.getEmail().equals(updateUserRequestModel.getEmail()))
            throw new UserAlreadyExistsException("El usuario con email "+updateUserRequestModel.getEmail()+" ya existe, utilice otro.");
        Role role = roleRepository.findByName(updateUserRequestModel.getRole()).orElseThrow(() -> new RoleException("El Rol "+ updateUserRequestModel.getRole() +" no existe."));
        User user = User.getInstance(findUser.getId(),
                updateUserRequestModel.getName(),
                updateUserRequestModel.getLastName(),
                updateUserRequestModel.getEmail(),
                findUser.getPassword(),
                findUser.getResetToken(),
                findUser.getTokenExpiryDate(),
                role);
        updateUserRepository.update(user);
    }
}
