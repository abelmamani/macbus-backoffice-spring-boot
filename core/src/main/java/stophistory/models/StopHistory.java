package stophistory.models;

import stop.models.Stop;
import stophistory.exceptions.StopHistoryException;

public class StopHistory {
    private String id;
    private String date;
    private String time;
    private Stop stop;

    private StopHistory(String id, String date, String time, Stop stop) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.stop = stop;
    }

    public static StopHistory getInstance(String id, String date, String time, Stop stop) {
        if(date == null)
            throw new StopHistoryException("La fecha es requerido.");
        if(time == null)
            throw new StopHistoryException("La hora es requerido.");
        if(stop == null)
            throw new StopHistoryException("La parada es requerido.");
        return new StopHistory(id, date, time, stop);
    }
    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Stop getStop() {
        return stop;
    }
}
