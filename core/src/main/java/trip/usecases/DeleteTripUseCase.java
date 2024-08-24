package trip.usecases;

import route.exceptions.RouteException;
import route.models.Route;
import route.models.RouteStatus;
import route.outputs.UpdateRouteRepository;
import trip.exceptions.TripException;
import trip.inputs.DeleteTripInput;
import trip.models.Trip;
import java.util.List;

public class DeleteTripUseCase implements DeleteTripInput {
    private UpdateRouteRepository updateRouteRepository;

    public DeleteTripUseCase(UpdateRouteRepository updateRouteRepository) {
        this.updateRouteRepository = updateRouteRepository;
    }

    @Override
    public RouteStatus deleteTrip(String busRouteName, String serviceName, String departureTime) {
        Route route = updateRouteRepository.findByLongName(busRouteName).orElseThrow(() -> new RouteException("La linea no existe."));
        if(!route.existTripByDepartureTimeAndService(departureTime, serviceName))
            throw new TripException("No existe un viaje con el servicio y hora de salida especificada.");
        Trip trip = route.getTripByDepartureTimeAndService(departureTime, serviceName);
        List<Trip> trips = route.getTrips();
        trips.remove(trip);
        RouteStatus status = trips.isEmpty() ? RouteStatus.WITH_STOPS : RouteStatus.WITH_TRIPS;
        Route updateRoute = Route.getInstance(route.getId(),
                route.getShortName(),
                route.getLongName(),
                route.getDescription(),
                route.getColor(),
                route.getTextColor(),
                status,
                route.getShapes(),
                route.getStopSequences(),
                trips);
        updateRouteRepository.deleteTripAndStopTimes(busRouteName, departureTime, serviceName);
        updateRouteRepository.update(updateRoute);
        return status;
    }
}
