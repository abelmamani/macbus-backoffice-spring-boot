package stop.outputs;

import stop.models.Stop;
import stop.models.StopStatus;

import java.util.Optional;

public interface UpdateStopRepository {
    boolean existsByName(String name);
    Optional<Stop> findByName(String name);
    void update(Stop stop);
}
