package busservice.usecases;

import busservice.exceptions.ServiceAlreadyExistException;
import busservice.exceptions.ServiceException;
import busservice.inputs.CreateServiceInput;
import busservice.models.ServiceModel;
import busservice.models.Service;
import busservice.outputs.CreateServiceRepository;
import calendardate.models.CalendarDate;
import utils.DateUtils;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreateServiceUseCase implements CreateServiceInput {
    private CreateServiceRepository createServiceRepository;

    public CreateServiceUseCase(CreateServiceRepository createServiceRepository) {
        this.createServiceRepository = createServiceRepository;
    }

    @Override
    public String createService(ServiceModel createServiceRequestModel) {
        LocalDate startDate = DateUtils.getLocalDate(createServiceRequestModel.getStartDate());
        LocalDate endDate = DateUtils.getLocalDate(createServiceRequestModel.getEndDate());
        if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now()))
            throw new ServiceException("La fecha de inicio y fin deben ser superiores a la fecha actual.");
        if (startDate.isAfter(endDate))
            throw new ServiceException("La fecha de inicio debe ser inferior a la fecha de fin.");
        if(createServiceRepository.existsByName(createServiceRequestModel.getName()))
            throw new ServiceAlreadyExistException("El servicio "+createServiceRequestModel.getName() + " ya existe.");
        Service service = Service.getInstance(null,
                createServiceRequestModel.getName(),
                createServiceRequestModel.getStartDate(),
                createServiceRequestModel.getEndDate(),
                new ArrayList<CalendarDate>());
        return createServiceRepository.save(service);
    }
}
