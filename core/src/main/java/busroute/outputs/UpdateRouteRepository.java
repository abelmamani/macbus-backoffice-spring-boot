package busroute.outputs;

import busroute.models.Route;
import busroute.models.RouteProgressStatus;
import java.util.Optional;

public interface UpdateRouteRepository {
    Optional<RouteProgressStatus> getProgressStatusByLongName(String longName);
    Optional<Route> findByLongName(String longName);
    void updateProgressStatus(String longName, RouteProgressStatus progressStatus);
    void update(Route route);
}
