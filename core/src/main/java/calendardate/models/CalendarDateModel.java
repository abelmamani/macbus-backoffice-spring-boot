package calendardate.models;

public class CalendarDateModel {
    private String date;
    private String service;

    public CalendarDateModel(){}

    public CalendarDateModel(String date, String service) {
        this.date = date;
        this.service = service;
    }

    public String getDate() {
        return date;
    }

    public String getService() {
        return service;
    }
}
