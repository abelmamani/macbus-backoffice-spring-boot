package stopsequence.inputs;

import stopsequence.models.StopSequenceResponseModel;
import java.util.List;

public interface GetStopSequencesByRouteInput {
    List<StopSequenceResponseModel> getAllStopSequences(String longName);
}
