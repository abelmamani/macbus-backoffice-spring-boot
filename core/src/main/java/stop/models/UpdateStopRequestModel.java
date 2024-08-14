package stop.models;

public class UpdateStopRequestModel {
    private String name;
    private Double latitude;
    private Double longitude;
    private UpdateStopRequestModel(){}
    private UpdateStopRequestModel(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static UpdateStopRequestModel getInstance(String name, Double latitude, Double longitude) {
        return new UpdateStopRequestModel(name, latitude, longitude);
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
}
