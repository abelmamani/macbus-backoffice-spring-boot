package route.outputs;

import route.models.Route;

public interface CreateRouteRepository {
    boolean existsByShortName(String shortName);
    boolean existsByLongName(String longName);
    Long save(Route route);
}
