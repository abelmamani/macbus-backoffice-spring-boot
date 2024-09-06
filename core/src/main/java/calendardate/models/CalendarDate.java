package calendardate.models;

import calendardate.excepctions.CalendarDateException;
import utils.DateUtils;

public class CalendarDate {
    private String id;
    private String date;

    public CalendarDate(){}
    public CalendarDate(String id, String date) {
        this.id = id;
        this.date = date;
    }

    public static CalendarDate getInstance(String id, String date) {
        if (!DateUtils.isValidDate(date)) {
            throw new CalendarDateException("La fecha no es válida.");
        }
        return new CalendarDate(id, date);
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
}
