package busroute.outputs;

import busroute.models.RouteGeneralInfoResponseModel;
import report.models.RouteStatusCountsResponseModel;
import java.util.Optional;

public interface GetRouteRepository {
    boolean existsByLongName(String longName);
    Optional<RouteGeneralInfoResponseModel> findByRouteLongName(String longName);
    RouteStatusCountsResponseModel getRouteStatusCounts();
}
