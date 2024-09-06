package ar.edu.undec.adapter.data.busroute.repoimplementations;

import ar.edu.undec.adapter.data.busroute.crud.RouteCRUD;
import ar.edu.undec.adapter.data.busroute.mapper.RouteDataMapper;
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
    public Optional<Route> findByLongName(String longName) {
        return routeCRUD.findByLongName(longName).map(RouteDataMapper::dataCoreMapper);
    }

    @Override
    public void deleteShapesByLongName(String longName) {
        routeCRUD.deleteShapesByLongName(longName);
    }

    @Override
    public void deleteStopSequenceByLongNameAndArrivalTime(String longName, String arrivalTime) {
        routeCRUD.deleteStopSequenceByLongNameAndArrivalTime(longName, arrivalTime);
    }

    @Override
    public void deleteTripAndStopTimes(String longName, String departureTime, String serviceName) {
        routeCRUD.deleteTripAndStopTimes(longName, departureTime, serviceName);
    }

    @Override
    public void update(Route route) {
        routeCRUD.save(RouteDataMapper.dataNodeMapper(route));
    }
}
