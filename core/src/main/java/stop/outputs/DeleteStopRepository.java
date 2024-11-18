package stop.outputs;

import stop.models.Stop;
import stop.models.StopAssignedStatus;

import java.util.Optional;

public interface DeleteStopRepository {
    Optional<Stop> findById(String id);
    void save(Stop stop);
    //boolean existsByNameAndStatus(String name, StopAssignedStatus status);
    //void deleteByName(String name);
}
