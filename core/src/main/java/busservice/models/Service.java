package busservice.models;

import audit.EntityStatus;
import busservice.exceptions.ServiceException;
import calendardate.models.CalendarDate;
import utils.DateUtils;
import java.util.List;

public class Service {
    private String id;
    private String name;
    private String startDate;
    private String endDate;
    private EntityStatus status;
    private List<CalendarDate> calendarDates;

    private Service(String id, String name, String startDate, String endDate, EntityStatus status, List<CalendarDate> calendarDates) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.calendarDates = calendarDates;
    }

    public static Service getInstance(String id, String name, String startDate, String endDate, EntityStatus status, List<CalendarDate> calendarDates) {
        if(name == null || name.trim().isEmpty())
            throw new ServiceException("El nombre del servicio es requerido.");
        if (!DateUtils.isValidDate(startDate))
            throw new ServiceException("La fecha de inicio no es válida.");
        if (!DateUtils.isValidDate(endDate))
            throw new ServiceException("La fecha de fin no es válida.");
        if (status == null)
            throw new ServiceException("Ll estado del servicio es requerido.");
        return new Service(id, name.trim(), startDate, endDate, status, calendarDates);
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

    public EntityStatus getStatus() {
        return status;
    }

    public List<CalendarDate> getCalendarDates() {
        return calendarDates;
    }
}
