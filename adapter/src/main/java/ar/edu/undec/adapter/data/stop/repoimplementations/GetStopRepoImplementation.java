package ar.edu.undec.adapter.data.stop.repoimplementations;

import ar.edu.undec.adapter.data.stop.crud.StopCRUD;
import ar.edu.undec.adapter.data.stop.mapper.StopDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import report.models.StopStatusCountsResponseModel;
import stop.models.Stop;
import stop.outputs.GetStopRepository;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GetStopRepoImplementation implements GetStopRepository {
    private StopCRUD stopCRUD;

    @Override
    public Optional<Stop> findByName(String name) {
        return stopCRUD.findByName(name).map(StopDataMapper::dataCoreMapper);
    }

    @Override
    public StopStatusCountsResponseModel getStopStatusCounts() {
        return stopCRUD.getStopStatusCounts();
    }
}
