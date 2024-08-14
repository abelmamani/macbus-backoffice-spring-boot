package stop.outputs;

import stop.models.StopStatus;

public interface DeleteStopRepository {
    boolean existsByNameAndStatus(String name, StopStatus status);
    void deleteByName(String name);
}
