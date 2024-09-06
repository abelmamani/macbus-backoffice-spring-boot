package busroute.inputs;

import busroute.models.RouteGeneralInfoResponseModel;

import java.util.Collection;

public interface GetRoutesInput {
    Collection<RouteGeneralInfoResponseModel> getRoutes();
}
