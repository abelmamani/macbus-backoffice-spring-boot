package tripupdate.outputs;

import tripupdate.models.TripUpdateResponseModel;
import java.util.List;

public interface TripUpdateRepository {
    List<TripUpdateResponseModel> findTripUpdates();
    boolean isTripUpdateRunning(String id);
    void stopTripUpdate(String id);
}
