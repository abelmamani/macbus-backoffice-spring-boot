package stop.usecases;

import stop.exceptions.StopNotExistsException;
import stop.inputs.GetStopInput;
import stop.models.Stop;
import stop.outputs.GetStopRepository;
import java.util.Optional;

public class GetStopUseCase implements GetStopInput {
    private GetStopRepository getStopRepository;

    public GetStopUseCase(GetStopRepository getStopRepository) {
        this.getStopRepository = getStopRepository;
    }

    @Override
    public Stop getStop(Long id) {
        return getStopRepository.findById(id).orElseThrow(() -> new StopNotExistsException("La parada con id " + id + " no existe."));
    }

    @Override
    public Stop getStopByName(String name) {
        return getStopRepository.findByName(name).orElseThrow(() -> new StopNotExistsException("La parada " + name + " no existe."));
    }
}
