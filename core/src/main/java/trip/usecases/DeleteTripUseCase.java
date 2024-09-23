package trip.usecases;

import busroute.exceptions.RouteException;
import busroute.models.Route;
import busroute.models.RouteStatus;
import busroute.outputs.UpdateRouteRepository;
import trip.exceptions.TripException;
import trip.inputs.DeleteTripInput;
import trip.models.Trip;
import trip.outputs.TripRepository;

import java.util.List;

public class DeleteTripUseCase implements DeleteTripInput {
    private UpdateRouteRepository updateRouteRepository;
    private TripRepository tripRepository;

    public DeleteTripUseCase(UpdateRouteRepository updateRouteRepository, TripRepository tripRepository) {
        this.updateRouteRepository = updateRouteRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public RouteStatus deleteTrip(String routeId, String tripId) {
        if(!tripRepository.existsTrip(routeId, tripId))
            throw new TripException("Error, el viaje a eliminar no existe.");
        updateRouteRepository.deleteTripAndStopTimes(id);
        if(updateRouteRepository.getTrips() < 1){
            updateRouteRepository.getRouteStatusByLongName();
        }
        return status;
    }
}
