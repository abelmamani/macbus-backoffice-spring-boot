package stopsequence.usecases;

import busroute.exceptions.RouteException;
import busroute.models.RouteProgressStatus;
import busroute.outputs.UpdateRouteRepository;
import stop.exceptions.StopException;
import stop.models.Stop;
import stop.models.StopAssignedStatus;
import stop.outputs.GetStopRepository;
import stopsequence.exceptions.StopSequenceException;
import stopsequence.inputs.CreateStopSequenceInput;
import stopsequence.models.CreateStopSequenceRequestModel;
import stopsequence.models.StopSequence;
import stopsequence.outputs.StopSequenceRepository;
import utils.TimeUtils;
import java.time.LocalTime;


public class CreateStopSequenceUseCase implements CreateStopSequenceInput {
    private UpdateRouteRepository updateRouteRepository;
    private GetStopRepository getStopRepository;
    private StopSequenceRepository stopSequenceRepository;

    public CreateStopSequenceUseCase(UpdateRouteRepository updateRouteRepository, GetStopRepository getStopRepository, StopSequenceRepository stopSequenceRepository) {
        this.updateRouteRepository = updateRouteRepository;
        this.getStopRepository = getStopRepository;
        this.stopSequenceRepository = stopSequenceRepository;
    }

    @Override
    public RouteProgressStatus createStopSequence(CreateStopSequenceRequestModel createStopSequenceRequestModel) {
        String routeName = createStopSequenceRequestModel.getBusRouteName();
        String arrivalTime =  TimeUtils.formatTime(createStopSequenceRequestModel.getArrivalTime());
        RouteProgressStatus progressStatus = updateRouteRepository.getProgressStatusByLongName(createStopSequenceRequestModel.getBusRouteName()).orElseThrow(() -> new RouteException("La linea no existe."));
        Stop stop = getStopRepository.findByName(createStopSequenceRequestModel.getStopName()).orElseThrow(() -> new StopException("La parada no existe."));
        if (progressStatus.equals(RouteProgressStatus.EMPTY) || progressStatus.equals(RouteProgressStatus.WITH_TRIPS))
            throw new StopSequenceException("No se puede registrar una secuencia de parada porque la línea no tiene un reocrrido definido o ya tiene viajes planificados.");
        if (progressStatus.equals(RouteProgressStatus.WITH_SHAPES) && createStopSequenceRequestModel.getArrivalTime() != LocalTime.of(0, 0))
            throw new StopSequenceException("La primera parada debe tener un tiempo de arribo de 00:00 ");
        if (progressStatus.equals(RouteProgressStatus.WITH_SHAPES) && createStopSequenceRequestModel.getDistanceTraveled() != 0)
            throw new StopSequenceException("La primera parada debe tener una distancia de 0");
        if ((progressStatus.equals(RouteProgressStatus.WITH_STOP) ||  progressStatus.equals(RouteProgressStatus.WITH_STOPS)) && createStopSequenceRequestModel.getDistanceTraveled() == 0)
            throw new StopSequenceException("La parada debe tener una distancia superior a 0");
        if(stopSequenceRepository.existsByRouteAndArrivalTime(routeName, arrivalTime))
            throw new StopSequenceException("Ya existe una parada con tiempo de arribo igual a "+arrivalTime);
        RouteProgressStatus newProgressStatus =  progressStatus.equals(RouteProgressStatus.WITH_SHAPES) ? RouteProgressStatus.WITH_STOP : RouteProgressStatus.WITH_STOPS;
        String sequenceId = stopSequenceRepository.save(StopSequence.getInstance(null,
                arrivalTime,
                createStopSequenceRequestModel.getDistanceTraveled(),
                createStopSequenceRequestModel.getHeadsign(),
                Stop.getInstance(stop.getId(),
                        stop.getName(),
                        stop.getLatitude(),
                        stop.getLongitude(),
                        StopAssignedStatus.ASSIGNED,
                        stop.getStatus())));
        stopSequenceRepository.addStopSequence(routeName, sequenceId, newProgressStatus);
        return newProgressStatus;
    }

    /*
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
        if(busRoute.getRouteStatus().equals(RouteStatus.WITH_STOPS) && !createStopSequenceRequestModel.getArrivalTime().isBefore(TimeUtils.parseTime(busRoute.getLastStopSequence().getArrivalTime())))
            throw new StopSequenceException("El tiempo de arribo debe ser inferior a " + busRoute.getLastStopSequence().getArrivalTime());
        if(busRoute.getRouteStatus().equals(RouteStatus.WITH_STOPS) && createStopSequenceRequestModel.getDistanceTraveled() >= busRoute.getLastStopSequence().getDistanceTraveled())
            throw new StopSequenceException("La distancia recorrida debe ser inferior a " + busRoute.getLastStopSequence().getDistanceTraveled());
        if(busRoute.existStopSequenceByArrivalTime(TimeUtils.formatTime(createStopSequenceRequestModel.getArrivalTime())))
            throw new StopSequenceException("Ya existe una parada con tiempo de arribo igual a "+createStopSequenceRequestModel.getArrivalTime());
        if(busRoute.existStopSequenceByDistanceTraveled(createStopSequenceRequestModel.getDistanceTraveled()))
            throw new StopSequenceException("Ya existe una parada con distancia recorrida igual a "+createStopSequenceRequestModel.getDistanceTraveled());
       List<StopSequence> stopSequences = busRoute.getStopSequences();
        boolean isInvalidSequence = stopSequences.stream()
                .anyMatch(sequence ->
                        (createStopSequenceRequestModel.getDistanceTraveled() > sequence.getDistanceTraveled()
                                && createStopSequenceRequestModel.getArrivalTime().isBefore(TimeUtils.parseTime(sequence.getArrivalTime()))) ||
                                (createStopSequenceRequestModel.getDistanceTraveled() < sequence.getDistanceTraveled()
                                        && createStopSequenceRequestModel.getArrivalTime().isAfter(TimeUtils.parseTime(sequence.getArrivalTime())))
                );
        if (isInvalidSequence)
            throw new StopSequenceException("La secuencia de parada no es válida. El tiempo de arribo y la distancia recorrida deben ser proporcionales.");
        stopSequences.add(StopSequence.getInstance(null,
                TimeUtils.formatTime(createStopSequenceRequestModel.getArrivalTime()),
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
    * */
}