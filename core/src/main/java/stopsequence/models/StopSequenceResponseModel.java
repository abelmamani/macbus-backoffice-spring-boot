package stopsequence.models;

public class StopSequenceResponseModel {
    private String id;
    private String arrivalTime;
    private Long distanceTraveled;
    private String headsign;
    private StopResponseModel stop;

    public StopSequenceResponseModel(){}
    public StopSequenceResponseModel(String id, String arrivalTime, Long distanceTraveled, String headsign, StopResponseModel stop) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.distanceTraveled = distanceTraveled;
        this.headsign = headsign;
        this.stop = stop;
    }

    public String getId() {
        return id;
    }
    public String getArrivalTime() {
        return arrivalTime;
    }
    public Long getDistanceTraveled() {
        return distanceTraveled;
    }
    public String getHeadsign() { return headsign; }
    public StopResponseModel getStop() {
        return stop;
    }
}
