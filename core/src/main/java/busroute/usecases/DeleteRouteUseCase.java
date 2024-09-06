package busroute.usecases;

import busroute.exceptions.RouteException;
import busroute.exceptions.RouteNotExistsException;
import busroute.inputs.DeleteRouteInput;
import busroute.models.RouteStatus;
import busroute.outputs.DeleteRouteRepository;

public class DeleteRouteUseCase implements DeleteRouteInput {
    private DeleteRouteRepository deleteRouteRepository;

    public DeleteRouteUseCase(DeleteRouteRepository deleteRouteRepository) {
        this.deleteRouteRepository = deleteRouteRepository;
    }

    @Override
    public void deleteRoute(String name) {
        RouteStatus routeStatus = deleteRouteRepository.getRouteStatusByLongName(name)
                .orElseThrow(() -> new RouteNotExistsException("La línea " + name + " no existe."));
        if (routeStatus.equals(RouteStatus.EMPTY)) {
            deleteRouteRepository.deleteByLongName(name);
        } else if (routeStatus.equals(RouteStatus.WITH_SHAPES)) {
            deleteRouteRepository.deleteRouteAndShapes(name);
        } else {
            throw new RouteException("La línea no puede eliminarse porque tiene paradas y/o viajes definidos.");
        }
    }
}
