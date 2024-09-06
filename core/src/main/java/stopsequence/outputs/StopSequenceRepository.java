package stopsequence.outputs;

import stopsequence.models.StopSequenceResponseModel;
import java.util.List;

public interface StopSequenceRepository {
    boolean isStopUsedInOtherRoutes(String stopName, String routeName);
    List<StopSequenceResponseModel> findAllByRouteLongName(String longName);
}
