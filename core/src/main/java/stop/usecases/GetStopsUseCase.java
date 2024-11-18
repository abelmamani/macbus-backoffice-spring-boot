package stop.usecases;

import stop.inputs.GetStopsInput;
import stop.models.Stop;
import stop.outputs.GetStopsRepository;
import java.util.Collection;

public class GetStopsUseCase implements GetStopsInput {
    private GetStopsRepository getStopsRepository;

    public GetStopsUseCase(GetStopsRepository getStopsRepository) {
        this.getStopsRepository = getStopsRepository;
    }

    @Override
    public Collection<Stop> getStops() {
        return getStopsRepository.findAll();
    }

    @Override
    public Collection<Stop> getActiveStops() {
        return getStopsRepository.findAllActiveStops();
    }
}
