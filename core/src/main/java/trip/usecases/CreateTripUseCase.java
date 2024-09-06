package trip.usecases;

import busroute.exceptions.RouteException;
import busroute.models.Route;
import busroute.models.RouteStatus;
import busroute.outputs.UpdateRouteRepository;
import busservice.exceptions.ServiceException;
import busservice.models.Service;
import busservice.outputs.GetServiceRepository;
import stoptime.models.StopTIme;
import trip.exceptions.TripException;
import trip.inputs.CreateTripInput;
import trip.models.CreateTripRequestModel;
import trip.models.Trip;
import trip.models.TripStatus;
import utils.TimeUtils;

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

        if (route.existTripByDepartureTimeAndService(createTripRequestModel.getDepartureTime().toString(), createTripRequestModel.getServiceName()))
            throw new TripException("Ya existe un viaje para el mismo servicio y hora de salida.");

        List<StopTIme> stopTimes = route.getStopSequences().stream()
                .map(s -> StopTIme.getInstance(
                            null,
                            TimeUtils.addLocalTimes(createTripRequestModel.getDepartureTime(), TimeUtils.parseTime(s.getArrivalTime())),
                            s.getDistanceTraveled(),
                            s.getHeadsign(),
                            s.getStop())
                    )
                .collect(Collectors.toList());
        List<Trip> trips = route.getTrips();
        trips.add(Trip.getInstance(
                null,
                TimeUtils.formatTime(createTripRequestModel.getDepartureTime()),
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
