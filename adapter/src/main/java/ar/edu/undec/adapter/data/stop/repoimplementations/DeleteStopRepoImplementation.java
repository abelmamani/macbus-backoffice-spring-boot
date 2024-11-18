package ar.edu.undec.adapter.data.stop.repoimplementations;

import ar.edu.undec.adapter.data.stop.crud.StopCRUD;
import ar.edu.undec.adapter.data.stop.mapper.StopDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stop.models.Stop;
import stop.outputs.DeleteStopRepository;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DeleteStopRepoImplementation implements DeleteStopRepository {
    private StopCRUD stopCRUD;

    @Override
    public Optional<Stop> findById(String id) {
        return stopCRUD.findById(id).map(StopDataMapper::dataCoreMapper);
    }

    @Override
    public void save(Stop stop) {
        stopCRUD.save(StopDataMapper.dataNodeMapper(stop));
    }
}
