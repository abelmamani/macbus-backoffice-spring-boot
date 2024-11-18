package ar.edu.undec.adapter.data.busroute.repoimplementations;

import ar.edu.undec.adapter.data.busroute.crud.RouteCRUD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import busroute.models.RouteProgressStatus;
import busroute.outputs.DeleteRouteRepository;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DeleteRouteRepoImplementation implements DeleteRouteRepository {
    private RouteCRUD routeCRUD;

    @Override
    public Optional<RouteProgressStatus> getProgressStatusByLongName(String longName) {
        return routeCRUD.getProgressStatusByLongName(longName);
    }

    @Override
    public void deleteByLongName(String longName) {
        routeCRUD.deleteByLongName(longName);
    }
}
