package stopsequence.models;

import java.time.LocalTime;

public class CreateStopSequenceRequestModel {
    private String busRouteName;
    private LocalTime arrivalTime;
    private Long distanceTraveled;
    private String headsign;
    private String stopName;
    private CreateStopSequenceRequestModel(){}

    public CreateStopSequenceRequestModel(String busRouteName, LocalTime arrivalTime, Long distanceTraveled, String headsign, String stopName) {
        this.busRouteName = busRouteName;
        this.arrivalTime = arrivalTime;
        this.distanceTraveled = distanceTraveled;
        this.headsign = headsign;
        this.stopName = stopName;
    }

    public String getBusRouteName() {
        return busRouteName;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public Long getDistanceTraveled() {
        return distanceTraveled;
    }

    public String getHeadsign() {
        return headsign;
    }

    public String getStopName() {
        return stopName;
    }
}
