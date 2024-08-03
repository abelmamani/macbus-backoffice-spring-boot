package ar.edu.undec.adapter.data.route.repoimplementations;

import ar.edu.undec.adapter.data.route.crud.RouteCRUD;
import ar.edu.undec.adapter.data.route.mapper.RouteDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import route.models.Route;
import route.outputs.GetRouteRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class GetRouteRepoImplementation implements GetRouteRepository {
    private RouteCRUD routeCRUD;

    @Override
    public Optional<Route> findById(Long id) {
        return routeCRUD.findById(id).map(RouteDataMapper::dataCoreMapper);
    }

    @Override
    public Optional<Route> findByLongName(String longName) {
        return routeCRUD.findByLongName(longName).map(RouteDataMapper::dataCoreMapper);
    }
}
