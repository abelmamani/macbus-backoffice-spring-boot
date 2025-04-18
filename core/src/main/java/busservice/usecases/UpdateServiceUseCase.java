package busservice.usecases;

import audit.EntityStatus;
import busservice.exceptions.ServiceAlreadyExistException;
import busservice.exceptions.ServiceException;
import busservice.exceptions.ServiceNotExistsException;
import busservice.inputs.UpdateServiceInput;
import busservice.models.Service;
import busservice.models.ServiceModel;
import busservice.outputs.UpdateServiceRepository;
import calendardate.models.CalendarDate;
import role.exceptions.RoleException;
import utils.DateUtils;
import java.time.LocalDate;

public class UpdateServiceUseCase implements UpdateServiceInput {
    private UpdateServiceRepository updateServiceRepository;

    public UpdateServiceUseCase(UpdateServiceRepository updateServiceRepository) {
        this.updateServiceRepository = updateServiceRepository;
    }

    @Override
    public void updateService(ServiceModel updateServiceRequestModel) {
        LocalDate startDate = DateUtils.getLocalDate(updateServiceRequestModel.getStartDate());
        LocalDate endDate = DateUtils.getLocalDate(updateServiceRequestModel.getEndDate());
        if (endDate.isBefore(LocalDate.now()))
            throw new ServiceException("La fecha de fin debe ser igual o posterior a la fecha actual.");
        if (startDate.isAfter(endDate))
            throw new ServiceException("La fecha de inicio debe ser anterior o igual a la fecha de fin.");
        Service foundService = updateServiceRepository.findById(updateServiceRequestModel.getId())
                .orElseThrow(() -> new ServiceNotExistsException("El servicio a actuailzar no existe."));
        EntityStatus newStatus = foundService.getStatus();
        if (newStatus.equals(EntityStatus.INACTIVE)) {
            validateStringField(updateServiceRequestModel.getStatus(), "estado del servicio");
            try {
                newStatus = EntityStatus.valueOf(updateServiceRequestModel.getStatus());
            } catch (IllegalArgumentException e) {
                throw new RoleException("El estado " + updateServiceRequestModel.getStatus()+ " no es válido.");
            }
        }
        if (updateServiceRepository.existsByName(updateServiceRequestModel.getName().trim()) && !foundService.getName().equals(updateServiceRequestModel.getName().trim()))
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
                    newStatus,
                    foundService.getCalendarDates()
            );
            updateServiceRepository.update(service);
        }
    private void validateStringField(String field, String fieldName) {
        if (field == null || field.isBlank()) {
            throw new RoleException("El " + fieldName + " es requerido.");
        }
    }
}
