package trip.models;

import java.time.LocalTime;

public class CreateTripRequestModel {
    private String busRouteName;
    private LocalTime departureTime;
    private String serviceName;
    private CreateTripRequestModel(){}

    public CreateTripRequestModel(String busRouteName, LocalTime departureTime, String serviceName) {
        this.busRouteName = busRouteName;
        this.departureTime = departureTime;
        this.serviceName = serviceName;
    }

    public String getBusRouteName() {
        return busRouteName;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public String getServiceName() {
        return serviceName;
    }
}
