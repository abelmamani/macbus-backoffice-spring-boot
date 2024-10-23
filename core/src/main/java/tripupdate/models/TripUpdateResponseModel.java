package tripupdate.models;

public class TripUpdateResponseModel {
    private String id;
    private Double latitude;
    private Double longitude;
    private Long timestamp;
    private String route;
    public TripUpdateResponseModel(){}

    public TripUpdateResponseModel(String id, Double latitude, Double longitude, Long timestamp, String route) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.route = route;
    }

    public String getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getRoute() {
        return route;
    }
}
