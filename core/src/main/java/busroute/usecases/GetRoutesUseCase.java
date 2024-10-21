package busroute.usecases;

import busroute.inputs.GetRoutesInput;
import busroute.models.RouteGeneralInfoResponseModel;
import busroute.outputs.GetRoutesRepository;
import java.util.Collection;

public class GetRoutesUseCase implements GetRoutesInput {
    private GetRoutesRepository getRoutesRepository;

    public GetRoutesUseCase(GetRoutesRepository getRoutesRepository) {
        this.getRoutesRepository = getRoutesRepository;
    }

    @Override
    public Collection<RouteGeneralInfoResponseModel> getRoutes() {
        return getRoutesRepository.findAll();
    }

    @Override
    public Collection<RouteGeneralInfoResponseModel> getRoutesWithTrips() {
        return getRoutesRepository.findRoutesWithTrips();
    }
}
