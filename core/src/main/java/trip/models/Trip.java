package trip.models;

import busservice.models.Service;
import stoptime.models.StopTIme;
import trip.exceptions.TripException;
import tripupdate.models.TripUpdate;

import java.util.List;

public class Trip {
    private String id;
    private String departureTime;
    private TripStatus tripStatus;
    private Service service;
    private TripUpdate tripUpdate;
    private List<StopTIme> stopTImes;

    private Trip(String id, String departureTime, TripStatus tripStatus, Service service, TripUpdate tripUpdate, List<StopTIme> stopTImes) {
        this.id = id;
        this.departureTime = departureTime;
        this.tripStatus = tripStatus;
        this.service = service;
        this.tripUpdate = tripUpdate;
        this.stopTImes = stopTImes;
    }

    public static Trip getInstance(String id, String departureTime, TripStatus tripStatus, Service service, TripUpdate tripUpdate, List<StopTIme> stopTImes) {
        if(departureTime == null)
            throw new TripException("La hora de salida del viaje es requerido.");
        if(tripStatus == null)
            throw new TripException("El estado del viaje es requerido.");
        if(service == null)
            throw new TripException("El servicio del viaje es requerido.");
        if(tripUpdate == null)
            throw new TripException("La actualizacion del viaje es requerido.");
        if(stopTImes == null)
            throw new TripException("El listado de tiempos de arribo es requerido.");
        return new Trip(id, departureTime, tripStatus, service, tripUpdate, stopTImes);
    }

    public String getId() {
        return id;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public Service getService() {
        return service;
    }

    public TripUpdate getTripUpdate() {
        return tripUpdate;
    }

    public List<StopTIme> getStopTImes() {
        return stopTImes;
    }
}
