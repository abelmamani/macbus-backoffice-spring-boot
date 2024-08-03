package route.inputs;

import route.models.Route;
import route.models.RouteGeneralInfoResponseModel;

import java.util.Collection;

public interface GetRoutesInput {
    Collection<RouteGeneralInfoResponseModel> getRoutes();
}
