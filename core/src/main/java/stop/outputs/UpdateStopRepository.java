package stop.outputs;

import stop.models.Stop;

public interface UpdateStopRepository {
    boolean existsByName(String name);
    void update(Stop stop);
}
