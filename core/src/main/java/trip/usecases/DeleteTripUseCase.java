package trip.usecases;
import busroute.models.RouteProgressStatus;
import busroute.outputs.UpdateRouteRepository;
import trip.exceptions.TripException;
import trip.inputs.DeleteTripInput;
import trip.outputs.TripRepository;

public class DeleteTripUseCase implements DeleteTripInput {
    private UpdateRouteRepository updateRouteRepository;
    private TripRepository tripRepository;

    public DeleteTripUseCase(UpdateRouteRepository updateRouteRepository, TripRepository tripRepository) {
        this.updateRouteRepository = updateRouteRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public RouteProgressStatus deleteTrip(String routeName, String tripId) {
        RouteProgressStatus status = RouteProgressStatus.WITH_TRIPS;
        if(!tripRepository.existsById(tripId))
            throw new TripException("Error, el viaje a eliminar no existe.");
        tripRepository.deleteTripAndStopTimes(tripId);
        if(tripRepository.countTripsByRoute(routeName) < 1){
            updateRouteRepository.updateProgressStatus(routeName, RouteProgressStatus.WITH_STOPS);
            status = RouteProgressStatus.WITH_STOPS;
        }
        return status;
    }
}
