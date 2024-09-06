package busroute.inputs;

import busroute.models.CreateRouteRequestModel;

public interface CreateRouteInput {
    String createRoute(CreateRouteRequestModel createRouteRequestModel);
}
