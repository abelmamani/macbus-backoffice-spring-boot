package ar.edu.undec.adapter.data.busroute.repoimplementations;

import ar.edu.undec.adapter.data.busroute.crud.RouteCRUD;
import busroute.models.RouteGeneralInfoResponseModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import busroute.outputs.GetRouteRepository;
import report.models.RouteStatusCountsResponseModel;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GetRouteRepoImplementation implements GetRouteRepository {
    private RouteCRUD routeCRUD;

    @Override
    public boolean existsByLongName(String longName) {
        return routeCRUD.existsByLongName(longName);
    }

    @Override
    public Optional<RouteGeneralInfoResponseModel> findByRouteLongName(String longName) {
        return routeCRUD.findByRouteLongName(longName);
    }

    @Override
    public RouteStatusCountsResponseModel getRouteStatusCounts() {
        return routeCRUD.getRouteStatusCounts();
    }
}
