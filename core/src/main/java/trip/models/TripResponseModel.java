package trip.models;

import java.time.LocalTime;
public class TripResponseModel {
    private String id;
    private String departureTime;
    private String tripStatus;
    private String service;
    public TripResponseModel(){}

    public TripResponseModel(String id, String departureTime, String tripStatus, String service) {
        this.id = id;
        this.departureTime = departureTime;
        this.tripStatus = tripStatus;
        this.service = service;
    }

    public String getId() {
        return id;
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
