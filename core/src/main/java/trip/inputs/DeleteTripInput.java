package trip.inputs;

import busroute.models.RouteStatus;

public interface DeleteTripInput {
    RouteStatus deleteTrip(String routeName, String tripId);
}
