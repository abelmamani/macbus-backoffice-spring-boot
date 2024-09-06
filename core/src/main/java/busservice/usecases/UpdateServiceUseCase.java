package busservice.usecases;

import busservice.exceptions.ServiceAlreadyExistException;
import busservice.exceptions.ServiceException;
import busservice.exceptions.ServiceNotExistsException;
import busservice.inputs.UpdateServiceInput;
import busservice.models.Service;
import busservice.models.ServiceModel;
import busservice.outputs.UpdateServiceRepository;
import calendardate.models.CalendarDate;
import utils.DateUtils;
import java.time.LocalDate;

public class UpdateServiceUseCase implements UpdateServiceInput {
    private UpdateServiceRepository updateServiceRepository;

    public UpdateServiceUseCase(UpdateServiceRepository updateServiceRepository) {
        this.updateServiceRepository = updateServiceRepository;
    }

    @Override
    public void updateService(String name, ServiceModel updateServiceRequestModel) {
        LocalDate startDate = DateUtils.getLocalDate(updateServiceRequestModel.getStartDate());
        LocalDate endDate = DateUtils.getLocalDate(updateServiceRequestModel.getEndDate());
        if (endDate.isBefore(LocalDate.now()))
            throw new ServiceException("La fecha de fin debe ser igual o posterior a la fecha actual.");
        if (startDate.isAfter(endDate))
            throw new ServiceException("La fecha de inicio debe ser anterior o igual a la fecha de fin.");
        Service foundService = updateServiceRepository.findByName(name)
                .orElseThrow(() -> new ServiceNotExistsException("El servicio " + name + " no existe."));
        if (updateServiceRepository.existsByName(updateServiceRequestModel.getName()) && !foundService.getName().equals(updateServiceRequestModel.getName()))
            throw new ServiceAlreadyExistException("El servicio con nombre " + updateServiceRequestModel.getName() + " ya existe.");
        if (!foundService.getStartDate().equals(updateServiceRequestModel.getStartDate()) && startDate.isBefore(LocalDate.now()))
            throw new ServiceException("La nueva fecha de inicio debe ser igual o posterior a la fecha actual.");
            for (CalendarDate calendarDate : foundService.getCalendarDates()) {
                LocalDate calendarLocalDate = LocalDate.parse(calendarDate.getDate());
                if (calendarLocalDate.isBefore(startDate) || calendarLocalDate.isAfter(endDate)) {
                    throw new ServiceException("El servicio tiene fechas en el calendario que quedan fuera del nuevo intervalo.");
                }
            }
            Service service = Service.getInstance(
                    foundService.getId(),
                    updateServiceRequestModel.getName(),
                    updateServiceRequestModel.getStartDate(),
                    updateServiceRequestModel.getEndDate(),
                    foundService.getCalendarDates()
            );
            updateServiceRepository.update(service);
        }
    }
