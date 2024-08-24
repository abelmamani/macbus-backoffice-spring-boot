package trip.models;

import java.time.LocalTime;
public class TripResponseModel {
    private String departureTime;
    private String tripStatus;
    private String service;
    public TripResponseModel(){}

    public TripResponseModel(String departureTime, String tripStatus, String service) {
        this.departureTime = departureTime;
        this.tripStatus = tripStatus;
        this.service = service;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public String getService() {
        return service;
    }
}
