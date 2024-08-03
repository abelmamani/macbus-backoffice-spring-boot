package route.outputs;

import route.models.Route;

public interface UpdateGeneralInfoRepository {
    boolean existsByShortName(String shortName);
    boolean existsByLongName(String longName);
    void update(Route route);
}
