package ar.edu.undec.adapter.data.service.repoimplementations;

import ar.edu.undec.adapter.data.service.crud.ServiceCRUD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import service.outputs.DeleteServiceRepository;

@AllArgsConstructor
@Service
public class DeleteServiceRepoImplementation implements DeleteServiceRepository {
    private ServiceCRUD serviceCRUD;

    @Override
    public boolean existsById(Long id) {
        return serviceCRUD.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        serviceCRUD.deleteById(id);
    }
}
