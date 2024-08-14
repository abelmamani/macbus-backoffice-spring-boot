package route.outputs;

import route.models.Route;

import java.time.LocalTime;
import java.util.Optional;

public interface UpdateRouteRepository {
    Optional<Route> findByLongName(String longName);
    void deleteShapesByLongName(String longName);
    void deleteStopSequenceByLongNameAndArrivalTime(String longName, LocalTime arrivalTime);
    void deleteTripAndStopTimes(String longName, LocalTime departureTime, String serviceName);
    void update(Route route);
}
