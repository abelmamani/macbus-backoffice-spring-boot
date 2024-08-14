package trip.models;

import service.models.Service;
import stoptime.models.StopTIme;
import trip.exceptions.TripException;

import java.time.LocalTime;
import java.util.List;

public class TripResponseModel {
    private LocalTime departureTime;
    private String tripStatus;
    private String service;
    public TripResponseModel(){}

    public TripResponseModel(LocalTime departureTime, String tripStatus, String service) {
        this.departureTime = departureTime;
        this.tripStatus = tripStatus;
        this.service = service;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public String getService() {
        return service;
    }
}
