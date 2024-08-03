package service.usecases;

import service.exceptions.ServiceAlreadyExistException;
import service.inputs.CreateServiceInput;
import service.models.CreateServiceRequestModel;
import service.models.Service;
import service.outputs.CreateServiceRepository;
import stop.exceptions.StopException;

import java.time.LocalDate;

public class CreateServiceUseCase implements CreateServiceInput {
    private CreateServiceRepository createServiceRepository;

    public CreateServiceUseCase(CreateServiceRepository createServiceRepository) {
        this.createServiceRepository = createServiceRepository;
    }

    @Override
    public Long createService(CreateServiceRequestModel createServiceRequestModel) {
        if(createServiceRepository.existsByName(createServiceRequestModel.getName()))
            throw new ServiceAlreadyExistException("El servicio "+createServiceRequestModel.getName() + "ya existe.");
        if(createServiceRequestModel.getStartDate().isBefore(LocalDate.now()) || createServiceRequestModel.getEndDate().isBefore(LocalDate.now()))
            throw new StopException("La fecha de inicio y fin deben ser superiores a la fecha actual.");
        if(createServiceRequestModel.getStartDate().isAfter(createServiceRequestModel.getEndDate()))
            throw new StopException("La fecha de inicio debe ser inferior a la fecha de fin.");
        Service service = Service.getInstance(null,
                createServiceRequestModel.getName(),
                createServiceRequestModel.getStartDate(),
                createServiceRequestModel.getEndDate());
        return createServiceRepository.save(service);
    }
}
