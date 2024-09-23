package ar.edu.undec.adapter.data.busroute.repoimplementations;

import ar.edu.undec.adapter.data.busroute.crud.RouteCRUD;
import ar.edu.undec.adapter.data.busroute.mapper.RouteDataMapper;
import busroute.models.RouteStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import busroute.models.Route;
import busroute.outputs.UpdateRouteRepository;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UpdateRouteRepoImplementation implements UpdateRouteRepository {
    private RouteCRUD routeCRUD;

    @Override
    public Optional<RouteStatus> getRouteStatusByLongName(String longName) {
        return routeCRUD.getRouteStatusByLongName(longName);
    }

    @Override
    public Optional<Route> findByLongName(String longName) {
        return routeCRUD.findByLongName(longName).map(RouteDataMapper::dataCoreMapper);
    }

    @Override
    public void updateRouteStatus(String longName, RouteStatus routeStatus) {
        routeCRUD.updateRouteStatus(longName, routeStatus);
    }

    @Override
    public void update(Route route) {
        routeCRUD.save(RouteDataMapper.dataNodeMapper(route));
    }
}
