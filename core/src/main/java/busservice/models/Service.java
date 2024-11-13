package busservice.models;

import busservice.exceptions.ServiceException;
import calendardate.models.CalendarDate;
import utils.DateUtils;

import java.util.List;

public class Service {
    private String id;
    private String name;
    private String startDate;
    private String endDate;
    private List<CalendarDate> calendarDates;

    private Service(String id, String name, String startDate, String endDate, List<CalendarDate> calendarDates) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.calendarDates = calendarDates;
    }
    public static Service getInstance(String id, String name, String startDate, String endDate, List<CalendarDate> calendarDates) {
        if(name == null || name.trim().isEmpty())
            throw new ServiceException("El nombre del servicio es requerido.");
        if (!DateUtils.isValidDate(startDate))
            throw new ServiceException("La fecha de inicio no es válida.");
        if (!DateUtils.isValidDate(endDate))
            throw new ServiceException("La fecha de fin no es válida.");
        return new Service(id, name.trim(), startDate, endDate, calendarDates);
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public List<CalendarDate> getCalendarDates() {
        return calendarDates;
    }
}
