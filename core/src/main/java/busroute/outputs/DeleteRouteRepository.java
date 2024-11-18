package busroute.outputs;

import busroute.models.RouteProgressStatus;
import java.util.Optional;

public interface DeleteRouteRepository {
    Optional<RouteProgressStatus> getProgressStatusByLongName(String longName);
    void deleteByLongName(String longName);
}
