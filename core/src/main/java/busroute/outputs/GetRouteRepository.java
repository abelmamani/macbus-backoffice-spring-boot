package busroute.outputs;

import busroute.models.Route;
import busroute.models.RouteGeneralInfoResponseModel;

import java.util.Optional;

public interface GetRouteRepository {
    boolean existsByLongName(String longName);
    RouteGeneralInfoResponseModel findByRouteLongName(String longName);
}
