package stop.models;

import stop.exceptions.StopException;

public class Stop {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;

    private Stop(Long id, String name, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Stop getInstance(Long id, String name, Double latitude, Double longitude) {
        if(name == null || name.trim().isEmpty())
            throw new StopException("El nombre de la parada es requerido.");
        if(latitude == null)
            throw new StopException("La latitud de la parada es requerido.");
        if(longitude == null)
            throw new StopException("La lingitud de la parada es requerido.");
        return new Stop(id, name, latitude, longitude);
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
