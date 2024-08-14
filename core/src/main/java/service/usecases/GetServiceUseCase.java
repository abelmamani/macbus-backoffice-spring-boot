package service.usecases;

import service.exceptions.ServiceNotExistsException;
import service.inputs.GetServiceInput;
import service.models.Service;
import service.outputs.GetServiceRepository;
import java.util.Optional;
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
