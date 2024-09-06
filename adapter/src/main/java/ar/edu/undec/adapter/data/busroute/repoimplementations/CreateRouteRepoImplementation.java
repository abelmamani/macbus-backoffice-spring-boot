package ar.edu.undec.adapter.data.busroute.repoimplementations;

import ar.edu.undec.adapter.data.busroute.crud.RouteCRUD;
import ar.edu.undec.adapter.data.busroute.mapper.RouteDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import busroute.models.Route;
import busroute.outputs.CreateRouteRepository;

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
    public String save(Route route) {
        return routeCRUD.save(RouteDataMapper.dataNodeMapper(route)).getId();
    }
}
