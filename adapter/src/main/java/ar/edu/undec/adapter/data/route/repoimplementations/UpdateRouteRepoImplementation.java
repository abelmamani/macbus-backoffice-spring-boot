package ar.edu.undec.adapter.data.route.repoimplementations;

import ar.edu.undec.adapter.data.route.crud.RouteCRUD;
import ar.edu.undec.adapter.data.route.mapper.RouteDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import route.models.Route;
import route.outputs.UpdateRouteRepository;

import java.time.LocalTime;
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
    public void deleteStopSequenceByLongNameAndArrivalTime(String longName, LocalTime arrivalTime) {
        routeCRUD.deleteStopSequenceByLongNameAndArrivalTime(longName, arrivalTime);
    }

    @Override
    public void deleteTripAndStopTimes(String longName, LocalTime departureTime, String serviceName) {
        routeCRUD.deleteTripAndStopTimes(longName, departureTime, serviceName);
    }

    @Override
    public void update(Route route) {
        routeCRUD.save(RouteDataMapper.dataNodeMapper(route));
    }
}
