package route.usecases;

import route.inputs.GetRoutesInput;
import route.models.RouteGeneralInfoResponseModel;
import route.outputs.GetRoutesRepository;
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
}
