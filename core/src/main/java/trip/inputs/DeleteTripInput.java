package trip.inputs;

import busroute.models.RouteStatus;

public interface DeleteTripInput {
    RouteStatus deleteTrip(String busRouteName, String serviceName, String departureTime);
}
