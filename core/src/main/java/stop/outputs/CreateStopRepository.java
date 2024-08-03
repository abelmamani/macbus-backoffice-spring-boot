package stop.outputs;

import stop.models.Stop;
public interface CreateStopRepository {
    boolean existsByName(String name);
    Long save(Stop stop);
}
