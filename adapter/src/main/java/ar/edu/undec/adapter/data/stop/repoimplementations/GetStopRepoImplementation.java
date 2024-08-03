package ar.edu.undec.adapter.data.stop.repoimplementations;

import ar.edu.undec.adapter.data.stop.crud.StopCRUD;
import ar.edu.undec.adapter.data.stop.mapper.StopDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stop.models.Stop;
import stop.outputs.GetStopRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class GetStopRepoImplementation implements GetStopRepository {
    private StopCRUD stopCRUD;

    @Override
    public Optional<Stop> findById(Long id) {
        return stopCRUD.findById(id).map(StopDataMapper::dataCoreMapper);
    }
}
