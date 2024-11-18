package stop.outputs;

import stop.models.Stop;

import java.util.Optional;

public interface UpdateStopRepository {
    boolean existsByName(String name);
    Optional<Stop> findById(String id);
    void update(Stop stop);
}
