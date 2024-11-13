package shape.models;

import shape.exceptions.ShapeException;
import utils.ValidatorUtils;

public class Shape {
    private String id;
    private Double latitude;
    private Double longitude;
    private Long sequence;
    private Long distanceTraveled;
    private Shape(){}
    private Shape(String id, Double latitude, Double longitude, Long sequence, Long distanceTraveled) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sequence = sequence;
        this.distanceTraveled = distanceTraveled;
    }

    public static Shape getInstance(String id, Double latitude, Double longitude, Long sequence, Long distanceTraveled) {
        if (sequence == null)
            throw new ShapeException("La secuencia es requerida.");
        if (sequence < 0)
            throw new ShapeException("La secuencia no debe ser negativa.");
        if (latitude == null)
            throw new ShapeException("La latitud del punto es requerida.");
        if (!ValidatorUtils.isValidLatitude(latitude))
            throw new ShapeException("La latitud del punto debe estar entre -90 y 90.");
        if (longitude == null)
            throw new ShapeException("La longitud del punto es requerida.");
        if (!ValidatorUtils.isValidLongitude(longitude))
            throw new ShapeException("La longitud del punto debe estar entre -180 y 180.");
        if (distanceTraveled == null)
            throw new ShapeException("La distancia recorrida es requerida.");
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

    public Long getSequence() {
        return sequence;
    }

    public Long getDistanceTraveled() {
        return distanceTraveled;
    }
}
