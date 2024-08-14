package stop.usecases;

import stop.exceptions.StopNotExistsException;
import stop.inputs.GetStopInput;
import stop.models.Stop;
import stop.outputs.GetStopRepository;

public class GetStopUseCase implements GetStopInput {
    private GetStopRepository getStopRepository;

    public GetStopUseCase(GetStopRepository getStopRepository) {
        this.getStopRepository = getStopRepository;
    }

    @Override
    public Stop getStop(String name) {
        return getStopRepository.findByName(name).orElseThrow(() -> new StopNotExistsException("La parada " + name + " no existe."));
    }
}
