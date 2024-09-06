package calendardate.usecases;

import calendardate.inputs.GetCalendarDatesByServiceInput;
import calendardate.models.CalendarDateModel;
import calendardate.outputs.CalendarDateRepository;
import java.util.List;

public class GetCalendarDatesByServiceUseCase implements GetCalendarDatesByServiceInput {
    private CalendarDateRepository calendarDateRepository;

    public GetCalendarDatesByServiceUseCase(CalendarDateRepository calendarDateRepository) {
        this.calendarDateRepository = calendarDateRepository;
    }

    @Override
    public List<CalendarDateModel> getAllCalendarDates() {
        return calendarDateRepository.findAllCalendarDates();
    }
}
