package user.usecases;
import user.exceptions.UserAlreadyExistsException;
import user.exceptions.UserInvalidException;
import user.inputs.CreateUserInput;
import user.models.CreateUserRequestModel;
import user.models.ERole;
import user.models.User;
import user.outputs.CreateUserRepository;

public class CreateUserUseCase implements CreateUserInput {
    private CreateUserRepository createUserRepository;

    public CreateUserUseCase(CreateUserRepository createUserRepository) {
        this.createUserRepository = createUserRepository;
    }

    @Override
    public Long createUser(CreateUserRequestModel createUserRequestModel) {
        if(createUserRepository.existsByEmail(createUserRequestModel.getEmail()))
            throw new UserAlreadyExistsException("El usuario con email " +createUserRequestModel.getEmail()+ " ya existe." );
        try {
            ERole role = ERole.valueOf(createUserRequestModel.getRole());
        } catch (IllegalArgumentException e) {
            throw new UserInvalidException("El Rol " + createUserRequestModel.getRole() +" no es valido.");
        }
        User user = User.getInstance(null, createUserRequestModel.getName(),
                createUserRequestModel.getLastName(),
                createUserRequestModel.getEmail(),
                createUserRepository.encodePassword(createUserRequestModel.getPassword()),
                ERole.valueOf(createUserRequestModel.getRole()));
        return createUserRepository.save(user);
    }
}
