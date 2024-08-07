package stopsequence.models;

import stop.models.Stop;

import java.time.LocalTime;

public class CreateStopSequenceRequestModel {
    private String busRouteName;
    private LocalTime arrivalTime;
    private Integer distanceTraveled;
    private Stop stop;

    public CreateStopSequenceRequestModel(String busRouteName, LocalTime arrivalTime, Integer distanceTraveled, Stop stop) {
        this.busRouteName = busRouteName;
        this.arrivalTime = arrivalTime;
        this.distanceTraveled = distanceTraveled;
        this.stop = stop;
    }

    public String getBusRouteName() {
        return busRouteName;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public Integer getDistanceTraveled() {
        return distanceTraveled;
    }

    public Stop getStop() {
        return stop;
    }
}
