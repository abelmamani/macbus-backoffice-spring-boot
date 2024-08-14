package trip.usecases;

import route.exceptions.RouteException;
import route.models.Route;
import route.models.RouteStatus;
import route.outputs.UpdateRouteRepository;
import service.exceptions.ServiceException;
import service.models.Service;
import service.outputs.GetServiceRepository;
import stoptime.models.StopTIme;
import trip.exceptions.TripException;
import trip.inputs.CreateTripInput;
import trip.models.CreateTripRequestModel;
import trip.models.Trip;
import trip.models.TripStatus;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


public class CreateTripUseCase implements CreateTripInput {
    private UpdateRouteRepository updateRouteRepository;
    private GetServiceRepository getServiceRepository;

    public CreateTripUseCase(UpdateRouteRepository updateRouteRepository, GetServiceRepository getServiceRepository) {
        this.updateRouteRepository = updateRouteRepository;
        this.getServiceRepository = getServiceRepository;
    }

    @Override
    public RouteStatus createTrip(CreateTripRequestModel createTripRequestModel) {
        Route route = updateRouteRepository.findByLongName(createTripRequestModel.getBusRouteName()).orElseThrow(() -> new RouteException("La linea no existe."));

        if (!route.getRouteStatus().equals(RouteStatus.WITH_STOPS) && !route.getRouteStatus().equals(RouteStatus.WITH_TRIPS))
            throw new TripException("No se puede registrar viaje porque la línea no tiene un reocrrido y/o secuencias de paradas definidos.");
        Service service = getServiceRepository.findByName(createTripRequestModel.getServiceName()).orElseThrow(() -> new ServiceException("El servicio no existe."));

        if (route.existTripByDepartureTimeAndService(createTripRequestModel.getDepartureTime(), createTripRequestModel.getServiceName()))
            throw new TripException("Ya existe un viaje para el mismo servicio y hora de salida.");

        List<StopTIme> stopTimes = route.getStopSequences().stream()
                .map(s -> StopTIme.getInstance(
                        null,
                        createTripRequestModel.getDepartureTime().plus(Duration.between(LocalTime.MIN, s.getArrivalTime())),
                        s.getDistanceTraveled(),
                        s.getStop()
                ))
                .collect(Collectors.toList());
        List<Trip> trips = route.getTrips();
        trips.add(Trip.getInstance(
                null,
                createTripRequestModel.getDepartureTime(),
                TripStatus.SCHEDULED,
                service,
                stopTimes
        ));
        updateRouteRepository.update(Route.getInstance(route.getId(),
                route.getShortName(),
                route.getLongName(),
                route.getDescription(),
                route.getColor(),
                route.getTextColor(),
                RouteStatus.WITH_TRIPS,
                route.getShapes(),
                route.getStopSequences(),
                trips));
        return RouteStatus.WITH_TRIPS;
    }
}
