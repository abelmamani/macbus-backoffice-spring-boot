package trip.inputs;

import busroute.models.RouteProgressStatus;
import trip.models.CreateTripRequestModel;

public interface CreateTripInput {
    RouteProgressStatus createTrip(CreateTripRequestModel createTripRequestModel);
}
