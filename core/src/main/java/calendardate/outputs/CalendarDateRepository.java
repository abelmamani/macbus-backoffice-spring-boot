package calendardate.outputs;

import calendardate.models.CalendarDateModel;
import java.util.List;

public interface CalendarDateRepository {
    boolean existsByDate(String date);
    void deleteByDate(String date);
    List<CalendarDateModel> findAllCalendarDates();
}
