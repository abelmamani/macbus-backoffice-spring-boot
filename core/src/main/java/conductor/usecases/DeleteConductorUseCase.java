package conductor.usecases;

import conductor.exceptions.ConductorException;
import conductor.inputs.DeleteConductorInput;
import conductor.outputs.ConductorRepository;

public class DeleteConductorUseCase implements DeleteConductorInput {
    private ConductorRepository conductorRepository;

    public DeleteConductorUseCase(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    @Override
    public void deleteConductor(String email) {
        if(!conductorRepository.existsByEmail(email))
            throw new ConductorException("No se puede eliminar, por que el conductor  " + email + " no existe.");
        conductorRepository.deleteByEmail(email);
    }
}
