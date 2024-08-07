package stopsequence.inputs;

import stopsequence.models.StopSequence;
import java.util.List;

public interface GetStopSequencesByRouteInput {
    List<StopSequence> getAllStopSequences(String longName);
}
