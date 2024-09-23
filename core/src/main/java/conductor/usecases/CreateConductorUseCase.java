package conductor.usecases;
import conductor.exceptions.ConductorException;
import conductor.inputs.CreateConductorInput;
import conductor.outputs.ConductorRepository;
import user.models.CreateUserRequestModel;
import user.models.ERole;
import user.models.User;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateConductorUseCase implements CreateConductorInput {
    private ConductorRepository conductorRepository;
    public CreateConductorUseCase(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }
    @Override
    public void createConductor(CreateUserRequestModel createUserRequestModel) {
        if(conductorRepository.existsByEmail(createUserRequestModel.getEmail()))
            throw new ConductorException("El conductor con email " +createUserRequestModel.getEmail()+ " ya existe." );
        User user = User.getInstance(null, createUserRequestModel.getName(),
                createUserRequestModel.getLastName(),
                createUserRequestModel.getEmail(),
                conductorRepository.encodePassword(createUserRequestModel.getPassword()),
                ERole.ROLE_CONDUCTOR,
                UUID.randomUUID().toString(),
                LocalDateTime.now().minusMinutes(1));
        conductorRepository.save(user);
    }
}
