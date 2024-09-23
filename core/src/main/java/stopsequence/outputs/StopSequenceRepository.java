package stopsequence.outputs;

import busroute.models.RouteStatus;
import stopsequence.models.StopSequence;
import stopsequence.models.StopSequenceResponseModel;
import java.util.List;
import java.util.Optional;

public interface StopSequenceRepository {
    Optional<StopSequence> findById(String id);
    boolean existsByRouteAndArrivalTime(String longName, String arrivalTime);
    boolean existsByStopName(String stopName);
    int countStopSequencesByRoute(String longName);
    void deleteStopSequence(String sequenceId);
    void addStopSequence(String longName, String sequenceId, RouteStatus routeStatus);
    String save(StopSequence stopSequence);
    List<StopSequenceResponseModel> findAllByRouteLongName(String longName);

}
