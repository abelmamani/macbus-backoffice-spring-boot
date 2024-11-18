package calendardate.usecases;

import audit.EntityStatus;
import busservice.exceptions.ServiceAlreadyExistException;
import busservice.exceptions.ServiceNotExistsException;
import busservice.models.Service;
import busservice.outputs.UpdateServiceRepository;
import calendardate.excepctions.CalendarDateException;
import calendardate.inputs.CreateCalendarDateInput;
import calendardate.models.CalendarDate;
import calendardate.models.CalendarDateModel;
import calendardate.outputs.CalendarDateRepository;
import utils.DateUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateCalendarDateUseCase implements CreateCalendarDateInput {
    private CalendarDateRepository calendarDateRepository;
    private UpdateServiceRepository updateServiceRepository;

    public CreateCalendarDateUseCase(CalendarDateRepository calendarDateRepository, UpdateServiceRepository updateServiceRepository) {
        this.calendarDateRepository = calendarDateRepository;
        this.updateServiceRepository = updateServiceRepository;
    }

    @Override
    public void createCalendarDate(CalendarDateModel calendarDateModel) {
        String dateStr = calendarDateModel.getDate();
        if (!DateUtils.isValidDate(dateStr))
            throw new CalendarDateException("La fecha de calendario no es válida.");
        LocalDate calendarDate = LocalDate.parse(dateStr);
        if (calendarDateRepository.existsByDate(dateStr))
            throw new ServiceAlreadyExistException("La fecha de calendario " + dateStr + " ya existe.");
        Service foundService = updateServiceRepository.findByName(calendarDateModel.getService())
                .orElseThrow(() -> new ServiceNotExistsException("El servicio " + calendarDateModel.getService() + " no existe."));

        if (calendarDate.isBefore(DateUtils.getLocalDate(foundService.getStartDate())) || calendarDate.isAfter(DateUtils.getLocalDate(foundService.getEndDate())))
            throw new CalendarDateException("La fecha de calendario debe estar dentro del rango de fechas del servicio.");
        List<CalendarDate> calendarDates = new ArrayList<>(foundService.getCalendarDates());
        calendarDates.add(CalendarDate.getInstance(null, dateStr));
        Service updatedService = Service.getInstance(
                foundService.getId(),
                foundService.getName(),
                foundService.getStartDate(),
                foundService.getEndDate(),
                foundService.getStatus(),
                calendarDates
        );
        updateServiceRepository.update(updatedService);
    }
}
