package busroute.inputs;

import busroute.models.Route;
import busroute.models.RouteGeneralInfoResponseModel;

public interface GetRouteInput {
    RouteGeneralInfoResponseModel getRouteGeneralInfoByName(String name);

}
