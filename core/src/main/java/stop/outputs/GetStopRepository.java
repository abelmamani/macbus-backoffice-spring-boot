package stop.outputs;

import report.models.StopStatusCountsResponseModel;
import stop.models.Stop;
import java.util.Optional;

public interface GetStopRepository {
    Optional<Stop> findByName(String name);
    StopStatusCountsResponseModel getStopStatusCounts();
}
