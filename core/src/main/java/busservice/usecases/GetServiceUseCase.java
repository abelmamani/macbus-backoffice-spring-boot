package busservice.usecases;

import busservice.exceptions.ServiceNotExistsException;
import busservice.inputs.GetServiceInput;
import busservice.models.Service;
import busservice.outputs.GetServiceRepository;

public class GetServiceUseCase implements GetServiceInput {
    private GetServiceRepository getServiceRepository;

    public GetServiceUseCase(GetServiceRepository getServiceRepository) {
        this.getServiceRepository = getServiceRepository;
    }

    @Override
    public Service getService(String name) {
        return getServiceRepository.findByName(name).orElseThrow(() -> new ServiceNotExistsException("El servicio " + name + " no existe."));
    }
}
