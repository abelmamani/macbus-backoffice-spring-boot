package trip.models;

import java.time.LocalTime;

public class CreateTripRequestModel {
    private String routeName;
    private LocalTime departureTime;
    private String serviceName;
    private CreateTripRequestModel(){}

    public CreateTripRequestModel(String routeName, LocalTime departureTime, String serviceName) {
        this.routeName = routeName;
        this.departureTime = departureTime;
        this.serviceName = serviceName;
    }

    public String getRouteName() {
        return routeName;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public String getServiceName() {
        return serviceName;
    }
}
