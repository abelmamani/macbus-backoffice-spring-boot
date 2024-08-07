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
        Optional<Route> route = getRouteRepository.findById(id);
        if(route.isEmpty())
            throw new RouteNotExistsException("La linea con id " + id + " no existe.");
        return route.get();
    }

    @Override
    public Route getRouteByName(String name) {
        Optional<Route> route = getRouteRepository.findByLongName(name);
        if (route.isEmpty())
            throw new RouteNotExistsException("La linea " + name + " no existe.");
        return route.get();
    }

    @Override
    public RouteResponseModel getRouteGeneralInfoByName(String name) {
        Optional<Route> route = getRouteRepository.findByLongName(name);
        if(route.isEmpty())
            throw new RouteNotExistsException("La linea " + name + " no existe.");
        RouteResponseModel routeResponseModel = RouteResponseModel.getInstance(route.get().getId(),
                route.get().getShortName(),
                route.get().getLongName(),
                route.get().getDescription(),
                route.get().getColor(),
                route.get().getTextColor(),
                route.get().getRouteStatus());
        return routeResponseModel;
    }
}
