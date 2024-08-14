package route.inputs;

import route.models.UpdateRouteRequestModel;

public interface UpdateGeneralInfoInput {
    void updateRoute(String longName, UpdateRouteRequestModel updateRouteRequestModel);
}
