package stop.models;

import stop.exceptions.StopException;

public class Stop {
    private String id;
    private String name;
    private Double latitude;
    private Double longitude;
    private StopStatus status;
    private Stop(){}

    private Stop(String id, String name, Double latitude, Double longitude, StopStatus status) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    public static Stop getInstance(String id, String name, Double latitude, Double longitude, StopStatus status) {
        if(name == null || name.trim().isEmpty())
            throw new StopException("El nombre de la parada es requerido.");
        if(latitude == null)
            throw new StopException("La latitud de la parada es requerido.");
        if(longitude == null)
            throw new StopException("La lingitud de la parada es requerido.");
        //if(status == null)
           // throw new StopException("El estado de la parada es requerido.");
        return new Stop(id, name, latitude, longitude, status);
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

    public StopStatus getStatus() {
        return status;
    }
}
