package busroute.outputs;

import busroute.models.Route;
import busroute.models.RouteStatus;
import java.util.Optional;

public interface UpdateRouteRepository {
    Optional<RouteStatus> getRouteStatusByLongName(String longName);
    Optional<Route> findByLongName(String longName);
    void updateRouteStatus(String longName, RouteStatus routeStatus);
    void update(Route route);
}
