package route.inputs;

import route.models.CreateRouteRequestModel;

public interface CreateRouteInput {
    Long createRoute(CreateRouteRequestModel createRouteRequestModel);
}
