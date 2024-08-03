package ar.edu.undec.adapter.data.route.repoimplementations;

import ar.edu.undec.adapter.data.route.crud.RouteCRUD;
import ar.edu.undec.adapter.data.route.mapper.RouteDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import route.models.Route;
import route.models.RouteGeneralInfoResponseModel;
import route.outputs.GetRoutesRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GetRoutesRepoImplementation implements GetRoutesRepository {
    private RouteCRUD routeCRUD;

    @Override
    public Collection<RouteGeneralInfoResponseModel> findAll() {
        return routeCRUD.findAllRoutesGeneralInfo();
    }
}
