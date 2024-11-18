package trip.inputs;

import busroute.models.RouteProgressStatus;

public interface DeleteTripInput {
    RouteProgressStatus deleteTrip(String routeName, String tripId);
}
