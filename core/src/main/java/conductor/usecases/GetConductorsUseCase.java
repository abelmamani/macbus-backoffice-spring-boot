package conductor.usecases;

import conductor.inputs.GetConductorsInput;
import conductor.models.ConductorResponseModel;
import conductor.outputs.ConductorRepository;
import java.util.Collection;

public class GetConductorsUseCase implements GetConductorsInput {
    private ConductorRepository conductorRepository;

    public GetConductorsUseCase(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    @Override
    public Collection<ConductorResponseModel> getConductors() {
        return conductorRepository.findConductors();
    }
}
