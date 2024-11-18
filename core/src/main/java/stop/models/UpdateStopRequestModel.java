package stop.models;

public class UpdateStopRequestModel {
    private String id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String status;
    private UpdateStopRequestModel(){}

    public UpdateStopRequestModel(String id, String name, Double latitude, Double longitude, String status) {
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

    public String getStatus() {
        return status;
    }
}
