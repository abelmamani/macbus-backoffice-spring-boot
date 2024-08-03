package stop.models;

public class CreateStopRequestModel {
    private String name;
    private Double latitude;
    private Double longitude;

    private CreateStopRequestModel(){}
    private CreateStopRequestModel(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static CreateStopRequestModel getInstance(String name, Double latitude, Double longitude) {
        return new CreateStopRequestModel(name, latitude, longitude);
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
