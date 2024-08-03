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
    public Service getService(Long id) {
        Optional<Service> service = getServiceRepository.findById(id);
        if(service.isEmpty())
            throw new ServiceNotExistsException("El servicio con id " + id + " no existe.");
        return service.get();
    }
}
