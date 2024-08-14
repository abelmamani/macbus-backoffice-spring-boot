package trip.usecases;

import trip.inputs.GetTripsByRouteInput;
import trip.models.TripResponseModel;
import trip.outputs.TripRepository;
import java.util.List;

public class GetTripsByRouteUseCase implements GetTripsByRouteInput {
    private TripRepository getTripsByRouteRepository;

    public GetTripsByRouteUseCase(TripRepository getTripsByRouteRepository) {
        this.getTripsByRouteRepository = getTripsByRouteRepository;
    }

    @Override
    public List<TripResponseModel> getAllTrips(String longName) {
        return getTripsByRouteRepository.findAllByRouteLongName(longName);
    }
}
