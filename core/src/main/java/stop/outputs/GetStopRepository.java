package stop.outputs;

import stop.models.Stop;
import java.util.Optional;
public interface GetStopRepository {
    Optional<Stop> findById(Long id);
}
