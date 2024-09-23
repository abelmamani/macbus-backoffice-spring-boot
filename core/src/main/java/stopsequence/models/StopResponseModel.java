package stopsequence.models;

import stop.models.StopStatus;

public class StopResponseModel {
    private String id;
    private String name;
    private Double latitude;
    private Double longitude;
    private StopStatus status;

    public StopResponseModel(){}

    public StopResponseModel(String id, String name, Double latitude, Double longitude, StopStatus status) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public StopStatus getStatus() {
        return status;
    }
}
