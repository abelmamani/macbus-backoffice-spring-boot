package ar.edu.undec.adapter.data.route.repoimplementations;

import ar.edu.undec.adapter.data.route.crud.RouteCRUD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import route.models.RouteStatus;
import route.outputs.DeleteRouteRepository;
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
