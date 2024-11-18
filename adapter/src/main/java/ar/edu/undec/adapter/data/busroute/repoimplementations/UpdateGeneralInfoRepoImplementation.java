package ar.edu.undec.adapter.data.busroute.repoimplementations;

import ar.edu.undec.adapter.data.busroute.crud.RouteCRUD;
import audit.EntityStatus;
import busroute.models.UpdateRouteRequestModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import busroute.outputs.UpdateGeneralInfoRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UpdateGeneralInfoRepoImplementation implements UpdateGeneralInfoRepository {
    private RouteCRUD routeCRUD;

    @Override
    public boolean existsByShortNameAndNotLongName(String shortName, String longName) {
        return routeCRUD.existsByShortNameAndNotLongName(shortName, longName);
    }

    @Override
    public boolean existsByLongName(String longName) {
        return routeCRUD.existsByLongName(longName);
    }

    @Override
    public Optional<EntityStatus> getStatusByLongName(String name){
        return routeCRUD.getStatusByLongName(name);
    }

    @Override
    public void update(String longName, UpdateRouteRequestModel updateRouteRequestModel) {
        routeCRUD.updateGeneralInfo(longName,
                updateRouteRequestModel.getShortName(),
                updateRouteRequestModel.getLongName(),
                updateRouteRequestModel.getDescription(),
                updateRouteRequestModel.getColor(),
                updateRouteRequestModel.getTextColor(),
                updateRouteRequestModel.getStatus());
    }
}
