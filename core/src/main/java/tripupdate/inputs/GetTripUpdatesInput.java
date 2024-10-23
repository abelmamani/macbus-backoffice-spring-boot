package tripupdate.inputs;

import tripupdate.models.TripUpdateResponseModel;
import java.util.List;

public interface GetTripUpdatesInput {
    List<TripUpdateResponseModel> getTripUpdates();
}
