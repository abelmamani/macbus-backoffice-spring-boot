package ar.edu.undec.adapter.data.stop.repoimplementations;

import ar.edu.undec.adapter.data.stop.crud.StopCRUD;
import ar.edu.undec.adapter.data.stop.mapper.StopDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stop.models.Stop;
import stop.outputs.UpdateStopRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UpdateStopRepoImplementation implements UpdateStopRepository {
    private StopCRUD stopCRUD;

    @Override
    public boolean existsByName(String name) {
        return stopCRUD.existsByName(name);
    }

    @Override
    public Optional<Stop> findById(String name) {
        return stopCRUD.findById(name).map(StopDataMapper::dataCoreMapper);
    }

    @Override
    public void update(Stop stop) {
        stopCRUD.save(StopDataMapper.dataNodeMapper(stop));
    }
}
