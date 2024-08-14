package ar.edu.undec.adapter.data.route.repoimplementations;

import ar.edu.undec.adapter.data.route.crud.RouteCRUD;
import ar.edu.undec.adapter.data.route.mapper.RouteDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import route.models.Route;
import route.outputs.UpdateGeneralInfoRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UpdateGeneralInfoRepoImplementation implements UpdateGeneralInfoRepository {
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
    public Optional<Route> findByLongName(String longName) {
        return routeCRUD.findByLongName(longName).map(RouteDataMapper::dataCoreMapper);
    }

    @Override
    public void update(Route route) {
        routeCRUD.save(RouteDataMapper.dataNodeMapper(route));
    }
}
