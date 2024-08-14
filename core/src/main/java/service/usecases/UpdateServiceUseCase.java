package service.usecases;

import service.exceptions.ServiceAlreadyExistException;
import service.exceptions.ServiceNotExistsException;
import service.inputs.GetServiceInput;
import service.inputs.UpdateServiceInput;
import service.models.Service;
import service.models.UpdateServiceRequestModel;
import service.outputs.UpdateServiceRepository;
public class UpdateServiceUseCase implements UpdateServiceInput {
    private UpdateServiceRepository updateServiceRepository;

    public UpdateServiceUseCase(UpdateServiceRepository updateServiceRepository) {
        this.updateServiceRepository = updateServiceRepository;
    }

    @Override
    public void updateService(String name, UpdateServiceRequestModel updateServiceRequestModel) {
        Service foundService = updateServiceRepository.findByName(name).orElseThrow(()-> new ServiceNotExistsException("El servicio "+name+" no existe."));
        if(updateServiceRepository.existsByName(updateServiceRequestModel.getName()) && !foundService.getName().equals(updateServiceRequestModel.getName()))
           throw new ServiceAlreadyExistException("El servicio con nombre " + updateServiceRequestModel.getName() + " ya existe.");
        Service service = Service.getInstance(foundService.getId(),
                updateServiceRequestModel.getName(),
                updateServiceRequestModel.getStartDate(),
                updateServiceRequestModel.getEndDate());
        updateServiceRepository.update(service);
    }
}
