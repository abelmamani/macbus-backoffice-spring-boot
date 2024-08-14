package shape.models;

import shape.exceptions.ShapeException;

public class Shape {
    private String id;
    private Double latitude;
    private Double longitude;
    private Integer sequence;
    private Integer distanceTraveled;
    private Shape(){}
    private Shape(String id, Double latitude, Double longitude, Integer sequence, Integer distanceTraveled) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sequence = sequence;
        this.distanceTraveled = distanceTraveled;
    }

    public static Shape getInstance(String id, Double latitude, Double longitude, Integer sequence, Integer distanceTraveled) {
        if (latitude < -90 || latitude > 90)
            throw new ShapeException("La latitud debe estar entre -90 y 90 grados.");

        if (longitude < -180 || longitude > 180)
            throw new ShapeException("La longitud debe estar entre -180 y 180 grados.");

        if (sequence < 0)
            throw new ShapeException("La secuencia no debe ser negativa.");

        if (distanceTraveled < 0)
            throw new ShapeException("La distancia recorrida no debe ser negativa.");

        return new Shape(id, latitude, longitude, sequence, distanceTraveled);
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

    public Integer getSequence() {
        return sequence;
    }

    public Integer getDistanceTraveled() {
        return distanceTraveled;
    }
}
