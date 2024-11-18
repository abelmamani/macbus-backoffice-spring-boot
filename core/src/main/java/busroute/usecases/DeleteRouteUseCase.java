package busroute.usecases;

import busroute.exceptions.RouteException;
import busroute.exceptions.RouteNotExistsException;
import busroute.inputs.DeleteRouteInput;
import busroute.models.RouteProgressStatus;
import busroute.outputs.DeleteRouteRepository;

public class DeleteRouteUseCase implements DeleteRouteInput {
    private DeleteRouteRepository deleteRouteRepository;

    public DeleteRouteUseCase(DeleteRouteRepository deleteRouteRepository) {
        this.deleteRouteRepository = deleteRouteRepository;
    }

    @Override
    public void deleteRoute(String name) {
        RouteProgressStatus routeStatus = deleteRouteRepository.getProgressStatusByLongName(name)
                .orElseThrow(() -> new RouteNotExistsException("La línea " + name + " no existe."));
        if (!routeStatus.equals(RouteProgressStatus.EMPTY) && !routeStatus.equals(RouteProgressStatus.WITH_SHAPES))
            throw new RouteException("La línea no puede eliminarse porque tiene paradas y/o viajes definidos.");
        deleteRouteRepository.deleteByLongName(name);
    }
}
