package route.outputs;

import route.models.Route;

import java.util.Optional;

public interface UpdateGeneralInfoRepository {
    boolean existsByShortName(String shortName);
    boolean existsByLongName(String longName);
    Optional<Route> findByLongName(String longName);
    void update(Route route);
}
