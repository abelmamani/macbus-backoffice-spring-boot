package route.inputs;

import route.models.Route;
import route.models.RouteResponseModel;

public interface GetRouteInput {
    Route getRouteById(Long id);
    Route getRouteByName(String name);
    RouteResponseModel getRouteGeneralInfoByName(String name);

}
