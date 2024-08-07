package stopsequence.outputs;
import stopsequence.models.StopSequence;

import java.util.List;

public interface GetStopSequencesByRouteRepository {
    List<StopSequence> findAllStopSequencesByRouteLongName(String longName);
}
