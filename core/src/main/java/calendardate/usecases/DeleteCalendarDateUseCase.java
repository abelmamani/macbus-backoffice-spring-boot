package calendardate.usecases;

import calendardate.excepctions.CalendarDateException;
import calendardate.excepctions.CalendarDateNotExistsException;
import calendardate.inputs.DeleteCalendarDateInput;
import calendardate.outputs.CalendarDateRepository;
import utils.DateUtils;

public class DeleteCalendarDateUseCase implements DeleteCalendarDateInput {
    private CalendarDateRepository calendarDateRepository;

    public DeleteCalendarDateUseCase(CalendarDateRepository calendarDateRepository) {
        this.calendarDateRepository = calendarDateRepository;
    }

    @Override
    public void deleteCalendarDate(String date) {
        if (!DateUtils.isValidDate(date))
            throw new CalendarDateException("La fecha de calendario no es válida.");
        if(!calendarDateRepository.existsByDate(date))
            throw new CalendarDateNotExistsException("La fecha de calendario " + date + " no existe.");
        calendarDateRepository.deleteByDate(date);
    }
}
