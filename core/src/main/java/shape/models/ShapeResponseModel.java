package shape.models;

public class ShapeResponseModel {
    private Double latitude;
    private Double longitude;
    private Integer sequence;
    private Integer distanceTraveled;
    private ShapeResponseModel(){}

    public ShapeResponseModel(Double latitude, Double longitude, Integer sequence, Integer distanceTraveled) {
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

    public Integer getSequence() {
        return sequence;
    }

    public Integer getDistanceTraveled() {
        return distanceTraveled;
    }
}
