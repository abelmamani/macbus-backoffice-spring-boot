package ar.edu.undec.adapter.data.route.repoimplementations;

import ar.edu.undec.adapter.data.route.crud.RouteCRUD;
import ar.edu.undec.adapter.data.route.mapper.RouteDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import route.models.Route;
import route.outputs.CreateRouteRepository;

@AllArgsConstructor
@Service
public class CreateRouteRepoImplementation implements CreateRouteRepository {
    private RouteCRUD routeCRUD;

    @Override
    public boolean existsByShortName(String shortName) {
        return routeCRUD.existsByShortName(shortName);
    }

    @Override
    public boolean existsByLongName(String longName) {
        return routeCRUD.existsByLongName(longName);
    }

    @Override
    public Long save(Route route) {
        return routeCRUD.save(RouteDataMapper.dataNodeMapper(route)).getId();
    }
}
