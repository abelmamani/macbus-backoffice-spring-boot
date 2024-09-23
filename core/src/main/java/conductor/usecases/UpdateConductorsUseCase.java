package conductor.usecases;

import conductor.exceptions.ConductorException;
import conductor.inputs.UpdateConductorInput;
import conductor.outputs.ConductorRepository;;
import user.exceptions.UserNotExistException;
import user.models.UpdateUserRequestModel;
import user.models.User;

public class UpdateConductorsUseCase implements UpdateConductorInput{
    private ConductorRepository conductorRepository;

    public UpdateConductorsUseCase(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    @Override
    public void updateConductor(String email, UpdateUserRequestModel updateUserRequestModel) {
        User conductor = conductorRepository.findByEmail(email).orElseThrow(() -> new UserNotExistException("El Conductor " + email +" no existe."));
        if(!email.equals(updateUserRequestModel.getEmail())){
            if(!conductor.getEmail().equals(updateUserRequestModel.getEmail()) && conductorRepository.existsByEmail(updateUserRequestModel.getEmail())){
                throw new ConductorException("El usuario con email "+updateUserRequestModel.getEmail()+" ya existe, utilice otro.");
            }
        }

        User user = User.getInstance(conductor.getId(),
                updateUserRequestModel.getName(),
                updateUserRequestModel.getLastName(),
                updateUserRequestModel.getEmail(),
                conductor.getPassword(),
                conductor.getRole(),
                conductor.getResetToken(),
                conductor.getTokenExpiryDate());
        conductorRepository.save(user);
    }
}
