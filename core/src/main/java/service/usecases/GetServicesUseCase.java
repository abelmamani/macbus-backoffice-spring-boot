package service.usecases;

import service.inputs.GetServicesInput;
import service.models.Service;
import service.outputs.GetServicesRepository;

import java.util.Collection;

public class GetServicesUseCase implements GetServicesInput {
    private GetServicesRepository getServicesRepository;

    public GetServicesUseCase(GetServicesRepository getServicesRepository) {
        this.getServicesRepository = getServicesRepository;
    }

    @Override
    public Collection<Service> getServices() {
        return getServicesRepository.findAll();
    }
}
