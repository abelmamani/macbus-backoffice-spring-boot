package busroute.inputs;
;
import busroute.models.UpdateRouteRequestModel;

public interface UpdateGeneralInfoInput {
    void updateRoute(String longName, UpdateRouteRequestModel updateRouteRequestModel);
}
