package tripupdate.models;

import tripupdate.exceptions.TripUpdateException;

public class TripUpdate {
    private String id;
    private Double latitude;
    private Double longitude;
    private Long timestamp;

    public TripUpdate(String id, Double latitude, Double longitude, Long timestamp) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public static TripUpdate getInstance(String id, Double latitude, Double longitude, Long timestamp) {
        if(latitude == null)
            throw new TripUpdateException("La latitud de la actualizacion del viaje es requerido.");
        if(longitude == null)
            throw new TripUpdateException("La longitude de la actualizacion de, viaje es requerido.");
        if(timestamp == null)
            throw new TripUpdateException("La marca de tiempo de la actualizacion del viaje es requerido.");
        return new TripUpdate(id, latitude, longitude, timestamp);
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

    public Long getTimestamp() {
        return timestamp;
    }
}
