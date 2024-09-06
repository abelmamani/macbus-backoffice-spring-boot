package busroute.outputs;

import busroute.models.RouteStatus;
import java.util.Optional;

public interface DeleteRouteRepository {
    Optional<RouteStatus> getRouteStatusByLongName(String longName);

    void deleteRouteAndShapes(String longName);
    void deleteByLongName(String longName);
}
