package ar.edu.undec.adapter.data.calendardate.mapper;

import ar.edu.undec.adapter.data.calendardate.models.CalendarDateNode;
import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import calendardate.models.CalendarDate;
import calendardate.models.CalendarDateModel;
import java.util.Map;

public class CalendarDateDataMapper {
    public static CalendarDate dataCoreMapper(CalendarDateNode calendarDate){
        try {
            return CalendarDate.getInstance(calendarDate.getId(),
                    calendarDate.getDate());
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from node to core");
        }
    }
    public static CalendarDateNode dataNodeMapper(CalendarDate calendarDate){
        try {
            return CalendarDateNode.builder()
                    .id(calendarDate.getId())
                    .date(calendarDate.getDate())
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }

    public static CalendarDateModel mapToCalendarDateModel(Map<String, Object> map) {
        return new CalendarDateModel((String) map.get("date"), (String) map.get("service"));
    }
}