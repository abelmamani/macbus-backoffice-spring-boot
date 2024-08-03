package ar.edu.undec.adapter.data.route.repoimplementations;

import ar.edu.undec.adapter.data.route.crud.RouteCRUD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import route.outputs.DeleteRouteRepository;

@AllArgsConstructor
@Service
public class DeleteRouteRepoImplementation implements DeleteRouteRepository {
    private RouteCRUD routeCRUD;

    @Override
    public boolean existsByLongName(String longName) {
        return routeCRUD.existsByLongName(longName);
    }

    @Override
    public void deleteByLongName(String longName) {
        routeCRUD.deleteByLongName(longName);
    }
}
