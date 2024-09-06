package ar.edu.undec.adapter.data.busroute.repoimplementations;

import ar.edu.undec.adapter.data.busroute.crud.RouteCRUD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import busroute.models.RouteStatus;
import busroute.outputs.DeleteRouteRepository;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DeleteRouteRepoImplementation implements DeleteRouteRepository {
    private RouteCRUD routeCRUD;

    @Override
    public Optional<RouteStatus> getRouteStatusByLongName(String longName) {
        return routeCRUD.getRouteStatusByLongName(longName);
    }

    @Override
    public void deleteRouteAndShapes(String longName) {
        routeCRUD.deleteRouteAndShapes(longName);
    }

    @Override
    public void deleteByLongName(String longName) {
        routeCRUD.deleteByLongName(longName);
    }
}
