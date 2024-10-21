package busroute.outputs;

import busroute.models.RouteGeneralInfoResponseModel;
import java.util.Collection;

public interface GetRoutesRepository {
    Collection<RouteGeneralInfoResponseModel> findAll();
    Collection<RouteGeneralInfoResponseModel> findRoutesWithTrips();
}
