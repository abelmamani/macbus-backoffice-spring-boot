package stop.outputs;

import stop.models.Stop;
public interface CreateStopRepository {
    boolean existsByName(String name);
    Stop save(Stop stop);
}
