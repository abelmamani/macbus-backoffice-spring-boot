package ar.edu.undec.adapter.data.stop.repoimplementations;

import ar.edu.undec.adapter.data.stop.crud.StopCRUD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stop.outputs.DeleteStopRepository;

@AllArgsConstructor
@Service
public class DeleteStopRepoImplementation implements DeleteStopRepository {
    private StopCRUD stopCRUD;

    @Override
    public boolean existsById(Long id) {
        return stopCRUD.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        stopCRUD.deleteById(id);
    }
}
