package service.usecases;

import service.exceptions.ServiceAlreadyExistException;
import service.inputs.GetServiceInput;
import service.inputs.UpdateServiceInput;
import service.models.Service;
import service.models.UpdateServiceRequestModel;
import service.outputs.UpdateServiceRepository;
public class UpdateServiceUseCase implements UpdateServiceInput {
    private UpdateServiceRepository updateServiceRepository;
    private GetServiceInput getServiceInput;

    public UpdateServiceUseCase(UpdateServiceRepository updateServiceRepository, GetServiceInput getServiceInput) {
        this.updateServiceRepository = updateServiceRepository;
        this.getServiceInput = getServiceInput;
    }

    @Override
    public void updateService(UpdateServiceRequestModel updateServiceRequestModel) {
        Service foundService = getServiceInput.getService(updateServiceRequestModel.getId());
        if(updateServiceRepository.existsByName(updateServiceRequestModel.getName()) && !foundService.getName().equals(updateServiceRequestModel.getName()))
           throw new ServiceAlreadyExistException("El servicio con nombre " + updateServiceRequestModel.getName() + " ya existe.");
        Service service = Service.getInstance(updateServiceRequestModel.getId(),
                updateServiceRequestModel.getName(),
                updateServiceRequestModel.getStartDate(),
                updateServiceRequestModel.getEndDate());
        updateServiceRepository.update(service);
    }
}
