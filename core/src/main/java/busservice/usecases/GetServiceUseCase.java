package busservice.usecases;

import audit.EntityStatus;
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
        return getServiceRepository.findByNameAndStatus(name, EntityStatus.ACTIVE).orElseThrow(() -> new ServiceNotExistsException("El servicio " + name + " no existe."));
    }
}
