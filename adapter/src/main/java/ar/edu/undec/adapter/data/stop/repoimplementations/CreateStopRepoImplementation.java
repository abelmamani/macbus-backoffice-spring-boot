package ar.edu.undec.adapter.data.stop.repoimplementations;

import ar.edu.undec.adapter.data.stop.crud.StopCRUD;
import ar.edu.undec.adapter.data.stop.mapper.StopDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stop.models.Stop;
import stop.outputs.CreateStopRepository;

@AllArgsConstructor
@Service
public class CreateStopRepoImplementation implements CreateStopRepository {
    private StopCRUD stopCRUD;

    @Override
    public boolean existsByName(String name) {
        return stopCRUD.existsByName(name);
    }

    @Override
    public Stop save(Stop stop) {
        return StopDataMapper.dataCoreMapper(stopCRUD.save(StopDataMapper.dataNodeMapper(stop)));
    }
}
