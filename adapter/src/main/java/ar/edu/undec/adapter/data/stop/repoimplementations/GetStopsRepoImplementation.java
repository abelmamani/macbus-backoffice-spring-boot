package ar.edu.undec.adapter.data.stop.repoimplementations;

import ar.edu.undec.adapter.data.stop.crud.StopCRUD;
import ar.edu.undec.adapter.data.stop.mapper.StopDataMapper;
import audit.EntityStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stop.models.Stop;
import stop.outputs.GetStopsRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GetStopsRepoImplementation implements GetStopsRepository {
    private StopCRUD stopCRUD;

    @Override
    public Collection<Stop> findAll() {
        return stopCRUD.findAll().stream().map(StopDataMapper::dataCoreMapper).collect(Collectors.toList());
    }

    @Override
    public Collection<Stop> findAllActiveStops() {
        return stopCRUD.findAllByStatus(EntityStatus.ACTIVE).stream().map(StopDataMapper::dataCoreMapper).collect(Collectors.toList());
    }
}
