package trip.usecases;

import route.inputs.GetRouteInput;
import route.inputs.UpdateRouteInput;
import route.models.Route;
import route.models.RouteStatus;
import service.inputs.GetServiceInput;
import service.models.Service;
import stoptime.models.StopTIme;
import trip.exceptions.TripException;
import trip.inputs.CreateTripInput;
import trip.models.CreateTripRequestModel;
import trip.models.Trip;
import trip.models.TripStatus;
import java.util.List;
import java.util.stream.Collectors;

public class CreateTripUseCase implements CreateTripInput {
    private GetServiceInput getServiceInput;
    private GetRouteInput getRouteInput;
    private UpdateRouteInput updateRouteInput;

    public CreateTripUseCase(GetServiceInput getServiceInput, GetRouteInput getRouteInput, UpdateRouteInput updateRouteInput) {
        this.getServiceInput = getServiceInput;
        this.getRouteInput = getRouteInput;
        this.updateRouteInput = updateRouteInput;
    }

    @Override
    public RouteStatus createTrip(CreateTripRequestModel createTripRequestModel) {
        Service service = getServiceInput.getServiceByName(createTripRequestModel.getServiceName());
        Route route = getRouteInput.getRouteByName(createTripRequestModel.getRouteName());

        boolean tripExists = route.getTrips().stream()
                .anyMatch(trip -> trip.getService().getName().equals(createTripRequestModel.getServiceName()) &&
                        trip.getDepartureTime().equals(createTripRequestModel.getDepartureTime()));
        if (tripExists)
            throw new TripException("Ya existe un viaje para el mismo servicio y hora de salida.");

        List<StopTIme> stopTimes = route.getStopSequences().stream()
                .map(s -> StopTIme.getInstance(null, s.getArrivalTime(), s.getDistanceTraveled(), s.getStop()))
                .collect(Collectors.toList());

        Trip trip = Trip.getInstance(null, route.getLongName(), createTripRequestModel.getDepartureTime(), TripStatus.SCHEDULED, service, stopTimes);

        List<Trip> trips = route.getTrips();
        trips.add(trip);

        Route updateRoute = Route.getInstance(route.getId(),
                route.getShortName(),
                route.getLongName(),
                route.getDescription(),
                route.getColor(),
                route.getTextColor(),
                RouteStatus.WITH_TRIPS,
                route.getShapes(),
                route.getStopSequences(),
                trips);
        updateRouteInput.updateRoute(updateRoute);

        return RouteStatus.WITH_TRIPS;
    }
}
