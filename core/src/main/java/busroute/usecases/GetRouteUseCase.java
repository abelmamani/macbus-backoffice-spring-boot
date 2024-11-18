package busroute.usecases;

import busroute.exceptions.RouteNotExistsException;
import busroute.inputs.GetRouteInput;
import busroute.models.RouteGeneralInfoResponseModel;
import busroute.outputs.GetRouteRepository;

public class GetRouteUseCase implements GetRouteInput {
    private GetRouteRepository getRouteRepository;

    public GetRouteUseCase(GetRouteRepository getRouteRepository) {
        this.getRouteRepository = getRouteRepository;
    }

    @Override
    public RouteGeneralInfoResponseModel getRouteGeneralInfoByName(String name) {
        return getRouteRepository.findByRouteLongName(name).orElseThrow(
                ()-> new RouteNotExistsException("La linea " + name + " no existe o no esta disponible."));
    }
}
