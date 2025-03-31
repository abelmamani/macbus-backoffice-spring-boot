package user.usecases;

import audit.EntityStatus;
import role.exceptions.RoleException;
import role.models.Role;
import role.outputs.RoleRepository;
import user.exceptions.UserAlreadyExistsException;
import user.inputs.CreateUserInput;
import user.models.CreateUserRequestModel;
import user.models.User;
import user.outputs.CreateUserRepository;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateUserUseCase implements CreateUserInput {
    private CreateUserRepository createUserRepository;
    private RoleRepository roleRepository;

    public CreateUserUseCase(CreateUserRepository createUserRepository, RoleRepository roleRepository) {
        this.createUserRepository = createUserRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public String createUser(CreateUserRequestModel createUserRequestModel) {
        if(createUserRepository.existsByEmail(createUserRequestModel.getEmail().trim()))
            throw new UserAlreadyExistsException("El usuario con email " +createUserRequestModel.getEmail()+ " ya existe." );
        Role role = roleRepository.findByName(createUserRequestModel.getRole()).orElseThrow(() -> new RoleException("El Rol "+ createUserRequestModel.getRole() +" no existe."));
        User user = User.getInstance(null, createUserRequestModel.getName(),
                createUserRequestModel.getLastName(),
                createUserRequestModel.getEmail(),
                createUserRepository.encodePassword(createUserRequestModel.getPassword()),
                UUID.randomUUID().toString(),
                LocalDateTime.now().minusMinutes(1),
                EntityStatus.ACTIVE,
                role);
        return createUserRepository.save(user);
    }
}
