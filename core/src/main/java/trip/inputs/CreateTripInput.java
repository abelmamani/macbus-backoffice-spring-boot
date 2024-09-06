package trip.inputs;

import busroute.models.RouteStatus;
import trip.models.CreateTripRequestModel;

public interface CreateTripInput {
    RouteStatus createTrip(CreateTripRequestModel createTripRequestModel);
}
