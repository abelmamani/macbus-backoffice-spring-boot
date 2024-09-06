package shape.models;

public class ShapeResponseModel {
    private Double latitude;
    private Double longitude;
    private Long sequence;
    private Long distanceTraveled;
    public ShapeResponseModel(){}
    public ShapeResponseModel(Double latitude, Double longitude, Long sequence, Long distanceTraveled) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.sequence = sequence;
        this.distanceTraveled = distanceTraveled;
    }
    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Long getSequence() {
        return sequence;
    }

    public Long getDistanceTraveled() {
        return distanceTraveled;
    }
}
