package trip.models;

import service.models.Service;
import stoptime.models.StopTIme;
import trip.exceptions.TripException;

import java.time.LocalTime;
import java.util.List;

public class Trip {
    private Long id;
    private LocalTime departureTime;
    private TripStatus tripStatus;
    private Service service;
    private List<StopTIme> stopTImes;

    private Trip(Long id, LocalTime departureTime, TripStatus tripStatus, Service service, List<StopTIme> stopTImes) {
        this.id = id;
        this.departureTime = departureTime;
        this.tripStatus = tripStatus;
        this.service = service;
        this.stopTImes = stopTImes;
    }

    public static Trip getInstance(Long id, LocalTime departureTime, TripStatus tripStatus, Service service, List<StopTIme> stopTImes) {
        if(departureTime == null)
            throw new TripException("La hora de salida del viaje es requerido.");
        if(tripStatus == null)
            throw new TripException("El estado del viaje es requerido.");
        if(service == null)
            throw new TripException("El servicio del viaje es requerido.");
        if(stopTImes == null)
            throw new TripException("El listado de tiempos de arribo es requerido.");
        return new Trip(id, departureTime, tripStatus, service, stopTImes);
    }

    public Long getId() {
        return id;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public Service getService() {
        return service;
    }

    public List<StopTIme> getStopTImes() {
        return stopTImes;
    }
}
