package ar.edu.undec.adapter.data.busroute.repoimplementations;

import ar.edu.undec.adapter.data.busroute.crud.RouteCRUD;
import ar.edu.undec.adapter.data.busroute.mapper.RouteDataMapper;
import audit.EntityStatus;
import busroute.models.RouteProgressStatus;
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
    public Optional<RouteProgressStatus> getProgressStatusByLongName(String longName) {
        return routeCRUD.getProgressStatusByLongName(longName);
    }

    @Override
    public Optional<Route> findByLongName(String longName) {
        return routeCRUD.findByLongNameAndStatus(longName, EntityStatus.ACTIVE).map(RouteDataMapper::dataCoreMapper);
    }

    @Override
    public void updateProgressStatus(String longName, RouteProgressStatus progressStatus) {
        routeCRUD.updateProgressStatus(longName, progressStatus);
    }

    @Override
    public void update(Route route) {
        routeCRUD.save(RouteDataMapper.dataNodeMapper(route));
    }
}
