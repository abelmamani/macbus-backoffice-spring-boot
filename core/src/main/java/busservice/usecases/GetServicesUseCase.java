package busservice.usecases;

import busservice.inputs.GetServicesInput;
import busservice.models.ServiceModel;
import busservice.outputs.GetServicesRepository;
import java.util.Collection;

public class GetServicesUseCase implements GetServicesInput {
    private GetServicesRepository getServicesRepository;

    public GetServicesUseCase(GetServicesRepository getServicesRepository) {
        this.getServicesRepository = getServicesRepository;
    }

    @Override
    public Collection<ServiceModel> getServices() {
        return getServicesRepository.findAll();
    }

    @Override
    public Collection<ServiceModel> getActiveServices() {
        return getServicesRepository.findAllActiveServices();
    }
}
