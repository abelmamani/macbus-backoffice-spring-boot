package stopsequence.usecases;

import route.inputs.GetRouteInput;
import route.inputs.UpdateRouteInput;
import route.models.Route;
import route.models.RouteStatus;
import stop.inputs.CreateStopInput;
import stop.models.CreateStopRequestModel;
import stop.models.Stop;
import stop.outputs.CreateStopRepository;
import stop.outputs.GetStopRepository;
import stopsequence.exceptions.StopSequenceException;
import stopsequence.inputs.CreateStopSequenceInput;
import stopsequence.models.CreateStopSequenceRequestModel;
import stopsequence.models.StopSequence;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class CreateStopSequenceUseCase implements CreateStopSequenceInput {
    private GetRouteInput getRouteInput;
    private CreateStopInput createStopInput;
    private UpdateRouteInput updateRouteInput;
    private GetStopRepository getStopRepository;

    public CreateStopSequenceUseCase(GetRouteInput getRouteInput, CreateStopInput createStopInput, UpdateRouteInput updateRouteInput, GetStopRepository getStopRepository) {
        this.getRouteInput = getRouteInput;
        this.createStopInput = createStopInput;
        this.updateRouteInput = updateRouteInput;
        this.getStopRepository = getStopRepository;
    }

    @Override
    public RouteStatus createStopSequence(CreateStopSequenceRequestModel createStopSequenceRequestModel) {
        Route busRoute = getRouteInput.getRouteByName(createStopSequenceRequestModel.getBusRouteName());
        if(busRoute.getRouteStatus().equals(RouteStatus.EMPTY) || busRoute.getRouteStatus().equals(RouteStatus.WITH_TRIPS))
            throw new StopSequenceException("No se puede registrar una secuencia de parada porque la línea no tiene un reocrrido definido o ya tiene viajes planificados.");
        if(busRoute.getRouteStatus().equals(RouteStatus.WITH_SHAPES) && createStopSequenceRequestModel.getArrivalTime() != LocalTime.of(0, 0))
            throw new StopSequenceException("La primera parada debe tener un tiempo de arribo de 00:00 ");
        if(busRoute.getRouteStatus().equals(RouteStatus.WITH_STOP) && createStopSequenceRequestModel.getDistanceTraveled() != 0)
            throw new StopSequenceException("La primera parada debe tener una distancia de 0");
        if(busRoute.getRouteStatus().equals(RouteStatus.WITH_STOP) && createStopSequenceRequestModel.getDistanceTraveled() != busRoute.getLastShape().getDistanceTraveled())
            throw new StopSequenceException("La última parada debe tener una distancia igual a " + busRoute.getLastShape().getDistanceTraveled());
        if(busRoute.getRouteStatus().equals(RouteStatus.WITH_STOPS) && !createStopSequenceRequestModel.getArrivalTime().isBefore(busRoute.getLastStopSequence().getArrivalTime()))
            throw new StopSequenceException("El tiempo de arribo debe ser inferior a " + busRoute.getLastStopSequence().getArrivalTime());
        if(busRoute.getRouteStatus().equals(RouteStatus.WITH_STOPS) && createStopSequenceRequestModel.getDistanceTraveled() >= busRoute.getLastStopSequence().getDistanceTraveled())
            throw new StopSequenceException("La distancia recorrida debe ser inferior a " + busRoute.getLastStopSequence().getDistanceTraveled());
        Optional<Stop> stop = getStopRepository.findByName(createStopSequenceRequestModel.getStop().getName());
        CreateStopRequestModel newStop = CreateStopRequestModel.getInstance(
                createStopSequenceRequestModel.getStop().getName(),
                createStopSequenceRequestModel.getStop().getLatitude(),
                createStopSequenceRequestModel.getStop().getLongitude());

        StopSequence stopSequence = StopSequence.getInstance(null,
                createStopSequenceRequestModel.getArrivalTime(),
                createStopSequenceRequestModel.getDistanceTraveled(),
                stop.isEmpty() ? createStopInput.createStop(newStop) : stop.get());

        List<StopSequence> stopSequences = busRoute.getStopSequences();
        stopSequences.add(stopSequence);

        Route route = Route.getInstance(busRoute.getId(),
                busRoute.getShortName(),
                busRoute.getLongName(),
                busRoute.getDescription(),
                busRoute.getColor(),
                busRoute.getTextColor(),
                busRoute.getRouteStatus().equals(RouteStatus.WITH_SHAPES) ? RouteStatus.WITH_STOP : RouteStatus.WITH_STOPS,
                busRoute.getShapes(),
                stopSequences,
                busRoute.getTrips());
        updateRouteInput.updateRoute(route);
        return busRoute.getRouteStatus().equals(RouteStatus.WITH_SHAPES) ? RouteStatus.WITH_STOP : RouteStatus.WITH_STOPS;
    }
}