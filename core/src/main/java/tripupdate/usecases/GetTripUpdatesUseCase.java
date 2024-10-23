package tripupdate.usecases;

import tripupdate.inputs.GetTripUpdatesInput;
import tripupdate.models.TripUpdateResponseModel;
import tripupdate.outputs.TripUpdateRepository;
import java.util.List;

public class GetTripUpdatesUseCase implements GetTripUpdatesInput {
    private TripUpdateRepository tripUpdateRepository;

    public GetTripUpdatesUseCase(TripUpdateRepository tripUpdateRepository) {
        this.tripUpdateRepository = tripUpdateRepository;
    }

    @Override
    public List<TripUpdateResponseModel> getTripUpdates() {
        return tripUpdateRepository.findTripUpdates();
    }
}
