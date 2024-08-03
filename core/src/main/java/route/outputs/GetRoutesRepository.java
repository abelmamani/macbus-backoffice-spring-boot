package route.outputs;

import route.models.RouteGeneralInfoResponseModel;

import java.util.Collection;

public interface GetRoutesRepository {
    Collection<RouteGeneralInfoResponseModel> findAll();
}
