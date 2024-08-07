package trip.usecases;

import trip.inputs.GetTripsByRouteInput;
import trip.models.Trip;
import trip.outputs.GetTripsByRouteRepository;

import java.util.List;

public class GetTripsByRouteUseCase implements GetTripsByRouteInput {
    private GetTripsByRouteRepository getTripsByRouteRepository;

    public GetTripsByRouteUseCase(GetTripsByRouteRepository getTripsByRouteRepository) {
        this.getTripsByRouteRepository = getTripsByRouteRepository;
    }

    @Override
    public List<Trip> getAllTrips(String longName) {
        return getTripsByRouteRepository.findAllByRouteLongName(longName);
    }
}
