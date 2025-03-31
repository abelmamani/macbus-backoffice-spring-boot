package user.usecases;

import audit.EntityStatus;
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
    public void updateUser(UpdateUserRequestModel updateUserRequestModel) {
        User findUser = updateUserRepository.findById(updateUserRequestModel.getId()).orElseThrow(() -> new UserNotExistException("El usuario a actualizar no existe."));
        EntityStatus newStatus = findUser.getStatus();
        if (newStatus.equals(EntityStatus.INACTIVE)) {
            try {
                validateStringField(updateUserRequestModel.getStatus(), "estado del usuario");
                newStatus = EntityStatus.valueOf(updateUserRequestModel.getStatus());
            } catch (IllegalArgumentException e) {
                throw new RoleException("El estado " + updateUserRequestModel.getStatus()+ " no es válido.");
            }
        }

        if(updateUserRepository.existsByEmail(updateUserRequestModel.getEmail().trim()) && !findUser.getEmail().equals(updateUserRequestModel.getEmail().trim()))
            throw new UserAlreadyExistsException("El usuario con email "+updateUserRequestModel.getEmail()+" ya existe, utilice otro.");
        Role role = roleRepository.findByName(updateUserRequestModel.getRole()).orElseThrow(() -> new RoleException("El Rol "+ updateUserRequestModel.getRole() +" no existe."));
        User user = User.getInstance(findUser.getId(),
                updateUserRequestModel.getName(),
                updateUserRequestModel.getLastName(),
                updateUserRequestModel.getEmail(),
                findUser.getPassword(),
                findUser.getResetToken(),
                findUser.getTokenExpiryDate(),
                newStatus,
                role);
        updateUserRepository.update(user);
    }
    private void validateStringField(String field, String fieldName) {
        if (field == null || field.isBlank()) {
            throw new RoleException("El " + fieldName + " es requerido.");
        }
    }
}
