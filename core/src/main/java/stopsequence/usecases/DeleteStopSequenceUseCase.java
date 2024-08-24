package stopsequence.usecases;

import route.exceptions.RouteNotExistsException;
import route.models.Route;
import route.models.RouteStatus;
import route.outputs.UpdateRouteRepository;
import stop.models.Stop;
import stop.models.StopStatus;
import stop.outputs.UpdateStopRepository;
import stopsequence.exceptions.StopSequenceException;
import stopsequence.inputs.DeleteStopSequenceInput;
import stopsequence.models.StopSequence;
import stopsequence.outputs.StopSequenceRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteStopSequenceUseCase implements DeleteStopSequenceInput {
    private final UpdateRouteRepository updateRouteRepository;
    private final StopSequenceRepository stopSequenceRepository;
    private final UpdateStopRepository updateStopRepository;

    public DeleteStopSequenceUseCase(UpdateRouteRepository updateRouteRepository, StopSequenceRepository stopSequenceRepository, UpdateStopRepository updateStopRepository) {
        this.updateRouteRepository = updateRouteRepository;
        this.stopSequenceRepository = stopSequenceRepository;
        this.updateStopRepository = updateStopRepository;
    }

    @Override
    public RouteStatus deleteStopSequence(String busRouteName, String arrivalTime) {
        Route busRoute = updateRouteRepository.findByLongName(busRouteName)
                .orElseThrow(() -> new RouteNotExistsException("La línea " + busRouteName + " no existe."));
        if (busRoute.getRouteStatus().equals(RouteStatus.WITH_TRIPS))
            throw new StopSequenceException("No se puede eliminar una secuencia de parada porque la línea ya tiene viajes planificados.");
        if (!busRoute.existStopSequenceByArrivalTime(arrivalTime))
            throw new StopSequenceException("No existe una parada con tiempo de arribo igual a " + arrivalTime);

        List<StopSequence> stopSequences = busRoute.getStopSequences().stream()
                .sorted(Comparator.comparing(StopSequence::getArrivalTime))
                .collect(Collectors.toList());
        StopSequence firstSequence = stopSequences.get(0);
        StopSequence lastSequence = stopSequences.get(stopSequences.size() - 1);
        if (stopSequences.size() == 2 && arrivalTime.equals(firstSequence.getArrivalTime()))
                throw new StopSequenceException("No se puede eliminar la primera parada en una secuencia con dos paradas.");
        if (stopSequences.size() > 2 && (arrivalTime.equals(firstSequence.getArrivalTime()) || arrivalTime.equals(lastSequence.getArrivalTime())))
                throw new StopSequenceException("No se puede eliminar la primera o última parada en una secuencia con más de dos paradas.");

        StopSequence stopSequence = stopSequences.stream()
                .filter(ss -> ss.getArrivalTime().equals(arrivalTime))
                .findFirst()
                .orElseThrow(() -> new StopSequenceException("No se encontró la secuencia de parada con el tiempo de arribo proporcionado."));
        stopSequences.remove(stopSequence);
        RouteStatus routeStatus = stopSequences.isEmpty() ? RouteStatus.WITH_SHAPES : (stopSequences.size() > 1 ? RouteStatus.WITH_STOPS : RouteStatus.WITH_STOP);
        Route updateRoute = Route.getInstance(
                busRoute.getId(),
                busRoute.getShortName(),
                busRoute.getLongName(),
                busRoute.getDescription(),
                busRoute.getColor(),
                busRoute.getTextColor(),
                routeStatus,
                busRoute.getShapes(),
                stopSequences,
                busRoute.getTrips()
        );

        updateRouteRepository.deleteStopSequenceByLongNameAndArrivalTime(busRouteName, arrivalTime);
        Stop stop = stopSequence.getStop();
        boolean stopUsedInRoute = stopSequences.stream().anyMatch(ss -> ss.getStop().getName().equals(stop.getName()));
        if (!stopUsedInRoute) {
            if(!stopSequenceRepository.isStopUsedInOtherRoutes(stop.getName(), busRoute.getLongName())) {
                updateStopRepository.update(Stop.getInstance(
                        stop.getId(),
                        stop.getName(),
                        stop.getLatitude(),
                        stop.getLongitude(),
                        StopStatus.UNASSIGNED
                ));
            }
        }
        updateRouteRepository.update(updateRoute);
        return routeStatus;
    }
}
