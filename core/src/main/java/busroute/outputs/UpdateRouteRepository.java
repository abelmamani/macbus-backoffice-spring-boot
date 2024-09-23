package busroute.outputs;

import busroute.models.Route;
import busroute.models.RouteStatus;

import java.util.Optional;

public interface UpdateRouteRepository {
    Optional<RouteStatus> getRouteStatusByLongName(String longName);
    Optional<Route> findByLongName(String longName);
    void deleteShapesByLongName(String longName);
    void deleteStopSequenceByLongNameAndArrivalTime(String longName, String arrivalTime);
    void deleteTripAndStopTimes(String longName, String departureTime, String serviceName);
    void update(Route route);
}
