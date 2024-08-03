package stop.outputs;

import stop.models.Stop;
import java.util.Collection;
public interface GetStopsRepository {
    Collection<Stop> findAll();
}
