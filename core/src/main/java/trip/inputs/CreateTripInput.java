package trip.inputs;

import route.models.RouteStatus;
import trip.models.CreateTripRequestModel;

public interface CreateTripInput {
    RouteStatus createTrip(CreateTripRequestModel createTripRequestModel);
}
