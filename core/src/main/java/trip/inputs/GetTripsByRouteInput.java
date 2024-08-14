package trip.inputs;

import trip.models.TripResponseModel;
import java.util.List;

public interface GetTripsByRouteInput {
    List<TripResponseModel> getAllTrips(String longName);
}
