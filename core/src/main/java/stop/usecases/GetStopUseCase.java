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
        Optional<Stop> stop = getStopRepository.findById(id);
        if(stop.isEmpty())
            throw new StopNotExistsException("La parada con id " + id + " no existe.");
        return stop.get();
    }
}
