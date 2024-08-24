package trip.inputs;

import route.models.RouteStatus;

public interface DeleteTripInput {
    RouteStatus deleteTrip(String busRouteName, String serviceName, String departureTime);
}
