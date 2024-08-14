package trip.inputs;

import route.models.RouteStatus;

import java.time.LocalTime;

public interface DeleteTripInput {
    RouteStatus deleteTrip(String busRouteName, String serviceName, LocalTime departureTime);
}
