package ar.edu.undec.adapter.data.calendardate.repoimplementations;

import ar.edu.undec.adapter.data.calendardate.crud.CalendarDateCRUD;
import ar.edu.undec.adapter.data.calendardate.mapper.CalendarDateDataMapper;
import calendardate.models.CalendarDateModel;
import calendardate.outputs.CalendarDateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CalendarDateRepoImplementation implements CalendarDateRepository {
    private CalendarDateCRUD calendarDateCRUD;
    @Override
    public boolean existsByDate(String date) {
        return calendarDateCRUD.existsByDate(date);
    }

    @Override
    public void deleteByDate(String date) {
        calendarDateCRUD.deleteByDate(date);
    }

    @Override
    public List<CalendarDateModel> findAllCalendarDates() {
        return calendarDateCRUD.findAllCalendarDates().stream().map(CalendarDateDataMapper::mapToCalendarDateModel).collect(Collectors.toList());

    }
}
