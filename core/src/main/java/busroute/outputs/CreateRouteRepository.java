package busroute.outputs;

import busroute.models.Route;

public interface CreateRouteRepository {
    boolean existsByShortName(String shortName);
    boolean existsByLongName(String longName);
    String save(Route route);
}
