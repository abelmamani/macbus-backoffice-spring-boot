package ar.edu.undec.adapter.data.route.repoimplementations;

import ar.edu.undec.adapter.data.route.crud.RouteCRUD;
import ar.edu.undec.adapter.data.route.mapper.RouteDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import route.models.Route;
import route.outputs.UpdateRouteRepository;

@AllArgsConstructor
@Service
public class UpdateRouteRepoImplementation implements UpdateRouteRepository {
    private RouteCRUD routeCRUD;
    @Override
    public void udpate(Route route) {
        routeCRUD.save(RouteDataMapper.dataNodeMapper(route));
    }
}
