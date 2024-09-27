package busroute.outputs;

import busroute.models.RouteGeneralInfoResponseModel;
import report.models.RouteStatusCountsResponseModel;

public interface GetRouteRepository {
    boolean existsByLongName(String longName);
    RouteGeneralInfoResponseModel findByRouteLongName(String longName);
    RouteStatusCountsResponseModel getRouteStatusCounts();
}
