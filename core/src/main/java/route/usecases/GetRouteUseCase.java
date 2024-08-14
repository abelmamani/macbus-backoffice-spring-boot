package route.usecases;

import route.exceptions.RouteNotExistsException;
import route.inputs.GetRouteInput;
import route.models.Route;
import route.models.RouteResponseModel;
import route.outputs.GetRouteRepository;
import java.util.Optional;

public class GetRouteUseCase implements GetRouteInput {
    private GetRouteRepository getRouteRepository;

    public GetRouteUseCase(GetRouteRepository getRouteRepository) {
        this.getRouteRepository = getRouteRepository;
    }
    @Override
    public Route getRouteById(Long id) {
        return getRouteRepository.findById(id).orElseThrow(() -> new RouteNotExistsException("La linea con id " + id + " no existe."));
    }

    @Override
    public Route getRouteByName(String name) {
        return getRouteRepository.findByLongName(name).orElseThrow( () -> new RouteNotExistsException("La linea " + name + " no existe."));
    }

    @Override
    public RouteResponseModel getRouteGeneralInfoByName(String name) {
        Route route = getRouteRepository.findByLongName(name).orElseThrow(() -> new RouteNotExistsException("La linea " + name + " no existe."));
        return RouteResponseModel.getInstance(route.getId(),
                route.getShortName(),
                route.getLongName(),
                route.getDescription(),
                route.getColor(),
                route.getTextColor(),
                route.getRouteStatus());
    }
}
