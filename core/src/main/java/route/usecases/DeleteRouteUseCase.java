package route.usecases;

import route.exceptions.RouteException;
import route.exceptions.RouteNotExistsException;
import route.inputs.DeleteRouteInput;
import route.models.RouteStatus;
import route.outputs.DeleteRouteRepository;

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
