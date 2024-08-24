package route.outputs;

import route.models.Route;
import java.util.Optional;

public interface UpdateRouteRepository {
    Optional<Route> findByLongName(String longName);
    void deleteShapesByLongName(String longName);
    void deleteStopSequenceByLongNameAndArrivalTime(String longName, String arrivalTime);
    void deleteTripAndStopTimes(String longName, String departureTime, String serviceName);
    void update(Route route);
}
