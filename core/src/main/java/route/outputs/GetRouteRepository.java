package route.outputs;

import route.models.Route;
import java.util.Optional;

public interface GetRouteRepository {
    Optional<Route> findByLongName(String longName);
}
