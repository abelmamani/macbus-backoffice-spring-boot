package busroute.outputs;

import audit.EntityStatus;
import busroute.models.UpdateRouteRequestModel;

import java.util.Optional;

public interface UpdateGeneralInfoRepository {
    boolean existsByShortNameAndNotLongName(String shortName, String longName);
    boolean existsByLongName(String longName);
    Optional<EntityStatus> getStatusByLongName(String longName);
    void update(String longName, UpdateRouteRequestModel updateRouteRequestModel);
}
