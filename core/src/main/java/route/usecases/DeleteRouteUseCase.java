package route.usecases;

import route.exceptions.RouteNotExistsException;
import route.inputs.DeleteRouteInput;
import route.outputs.DeleteRouteRepository;

public class DeleteRouteUseCase implements DeleteRouteInput {
    private DeleteRouteRepository deleteRouteRepository;

    public DeleteRouteUseCase(DeleteRouteRepository deleteRouteRepository) {
        this.deleteRouteRepository = deleteRouteRepository;
    }

    @Override
    public void deleteRoute(String name) {
        if(!deleteRouteRepository.existsByLongName(name))
            throw new RouteNotExistsException("La linea " + name + " no existe.");
        deleteRouteRepository.deleteByLongName(name);
    }
}
