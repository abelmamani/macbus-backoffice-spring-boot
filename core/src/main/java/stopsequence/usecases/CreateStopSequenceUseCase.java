package stopsequence.usecases;

import route.exceptions.RouteException;
import route.models.Route;
import route.models.RouteStatus;
import route.outputs.UpdateRouteRepository;
import stop.exceptions.StopException;
import stop.models.Stop;
import stop.models.StopStatus;
import stop.outputs.GetStopRepository;
import stopsequence.exceptions.StopSequenceException;
import stopsequence.inputs.CreateStopSequenceInput;
import stopsequence.models.CreateStopSequenceRequestModel;
import stopsequence.models.StopSequence;
import java.time.LocalTime;
import java.util.List;

public class CreateStopSequenceUseCase implements CreateStopSequenceInput {
    private UpdateRouteRepository updateRouteRepository;
    private GetStopRepository getStopRepository;

    public CreateStopSequenceUseCase(UpdateRouteRepository updateRouteRepository, GetStopRepository getStopRepository) {
        this.updateRouteRepository = updateRouteRepository;
        this.getStopRepository = getStopRepository;
    }

    @Override
    public RouteStatus createStopSequence(CreateStopSequenceRequestModel createStopSequenceRequestModel) {
        Route busRoute = updateRouteRepository.findByLongName(createStopSequenceRequestModel.getBusRouteName()).orElseThrow(() -> new RouteException("La linea no existe."));
        Stop stop = getStopRepository.findByName(createStopSequenceRequestModel.getStopName()).orElseThrow(() -> new StopException("La parada no existe."));
        RouteStatus routeStatus =  busRoute.getRouteStatus().equals(RouteStatus.WITH_SHAPES) ? RouteStatus.WITH_STOP : RouteStatus.WITH_STOPS;
        if (busRoute.getRouteStatus().equals(RouteStatus.EMPTY) || busRoute.getRouteStatus().equals(RouteStatus.WITH_TRIPS))
            throw new StopSequenceException("No se puede registrar una secuencia de parada porque la línea no tiene un reocrrido definido o ya tiene viajes planificados.");
        if (busRoute.getRouteStatus().equals(RouteStatus.WITH_SHAPES) && createStopSequenceRequestModel.getArrivalTime() != LocalTime.of(0, 0))
            throw new StopSequenceException("La primera parada debe tener un tiempo de arribo de 00:00 ");
        if (busRoute.getRouteStatus().equals(RouteStatus.WITH_SHAPES) && createStopSequenceRequestModel.getDistanceTraveled() != 0)
            throw new StopSequenceException("La primera parada debe tener una distancia de 0");
        if (busRoute.getRouteStatus().equals(RouteStatus.WITH_STOP) && !createStopSequenceRequestModel.getDistanceTraveled().equals(busRoute.getLastShape().getDistanceTraveled()))
            throw new StopSequenceException("La última parada debe tener una distancia igual a " + busRoute.getLastShape().getDistanceTraveled());
        if(busRoute.getRouteStatus().equals(RouteStatus.WITH_STOPS) && !createStopSequenceRequestModel.getArrivalTime().isBefore(busRoute.getLastStopSequence().getArrivalTime()))
            throw new StopSequenceException("El tiempo de arribo debe ser inferior a " + busRoute.getLastStopSequence().getArrivalTime());
        if(busRoute.getRouteStatus().equals(RouteStatus.WITH_STOPS) && createStopSequenceRequestModel.getDistanceTraveled() >= busRoute.getLastStopSequence().getDistanceTraveled())
            throw new StopSequenceException("La distancia recorrida debe ser inferior a " + busRoute.getLastStopSequence().getDistanceTraveled());
        if(busRoute.existStopSequenceByArrivalTime(createStopSequenceRequestModel.getArrivalTime()))
            throw new StopSequenceException("Ya existe una parada con tiempo de arribo igual a "+createStopSequenceRequestModel.getArrivalTime());
        if(busRoute.existStopSequenceByDistanceTraveled(createStopSequenceRequestModel.getDistanceTraveled()))
            throw new StopSequenceException("Ya existe una parada con distancia recorrida igual a "+createStopSequenceRequestModel.getDistanceTraveled());
       List<StopSequence> stopSequences = busRoute.getStopSequences();
        boolean isInvalidSequence = stopSequences.stream()
                .anyMatch(sequence ->
                        (createStopSequenceRequestModel.getDistanceTraveled() > sequence.getDistanceTraveled()
                                && createStopSequenceRequestModel.getArrivalTime().isBefore(sequence.getArrivalTime())) ||
                                (createStopSequenceRequestModel.getDistanceTraveled() < sequence.getDistanceTraveled()
                                        && createStopSequenceRequestModel.getArrivalTime().isAfter(sequence.getArrivalTime()))
                );
        if (isInvalidSequence)
            throw new StopSequenceException("La secuencia de parada no es válida. El tiempo de arribo y la distancia recorrida deben ser proporcionales.");
        stopSequences.add(StopSequence.getInstance(null,
                createStopSequenceRequestModel.getArrivalTime(),
                createStopSequenceRequestModel.getDistanceTraveled(),
                createStopSequenceRequestModel.getHeadsign(),
                Stop.getInstance(stop.getId(),
                        stop.getName(),
                        stop.getLatitude(),
                        stop.getLongitude(),
                        stop.getStatus().equals(StopStatus.ASSIGNED) ? stop.getStatus() : StopStatus.ASSIGNED)));
        updateRouteRepository.update(Route.getInstance(busRoute.getId(),
                busRoute.getShortName(),
                busRoute.getLongName(),
                busRoute.getDescription(),
                busRoute.getColor(),
                busRoute.getTextColor(),
                routeStatus,
                busRoute.getShapes(),
                stopSequences,
                busRoute.getTrips()));
       return routeStatus;
    }
}