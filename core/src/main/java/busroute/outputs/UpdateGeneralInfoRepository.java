package busroute.outputs;

import busroute.models.UpdateRouteRequestModel;

public interface UpdateGeneralInfoRepository {
    boolean existsByShortNameAndNotLongName(String shortName, String longName);
    boolean existsByLongName(String longName);
    void update(String longName, UpdateRouteRequestModel updateRouteRequestModel);
}
