package stop.models;

public class UpdateStopRequestModel {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private UpdateStopRequestModel(){}
    private UpdateStopRequestModel(Long id, String name, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static UpdateStopRequestModel getInstance(Long id, String name, Double latitude, Double longitude) {
        return new UpdateStopRequestModel(id, name, latitude, longitude);
    }

    public Long getId() {
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
}
