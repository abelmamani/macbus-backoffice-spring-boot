package ar.edu.undec.adapter.data.busroute.repoimplementations;

import ar.edu.undec.adapter.data.busroute.crud.RouteCRUD;
import ar.edu.undec.adapter.data.busroute.mapper.RouteDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import busroute.models.RouteGeneralInfoResponseModel;
import busroute.outputs.GetRoutesRepository;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GetRoutesRepoImplementation implements GetRoutesRepository {
    private RouteCRUD routeCRUD;

    @Override
    public Collection<RouteGeneralInfoResponseModel> findAll() {
        return routeCRUD.findAllRoutesGeneralInfo().stream().map(RouteDataMapper::mapToGeneralInfo).collect(Collectors.toList());
    }
}
