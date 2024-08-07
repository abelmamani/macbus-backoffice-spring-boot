package trip.inputs;

import trip.models.Trip;

import java.util.List;

public interface GetTripsByRouteInput {
    List<Trip> getAllTrips(String longName);
}
