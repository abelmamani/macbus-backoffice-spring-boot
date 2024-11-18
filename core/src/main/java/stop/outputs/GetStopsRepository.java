package stop.outputs;

import audit.EntityStatus;
import stop.models.Stop;
import java.util.Collection;

public interface GetStopsRepository {
    Collection<Stop> findAll();
    Collection<Stop> findAllActiveStops();
}
