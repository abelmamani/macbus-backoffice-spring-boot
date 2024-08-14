package ar.edu.undec.adapter.data.stop.repoimplementations;

import ar.edu.undec.adapter.data.stop.crud.StopCRUD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stop.models.StopStatus;
import stop.outputs.DeleteStopRepository;

@AllArgsConstructor
@Service
public class DeleteStopRepoImplementation implements DeleteStopRepository {
    private StopCRUD stopCRUD;
    @Override
    public boolean existsByNameAndStatus(String name, StopStatus status) {
        return stopCRUD.existsByNameAndStatus(name, status);
    }

    @Override
    public void deleteByName(String name) {
        stopCRUD.deleteByName(name);
    }
}
