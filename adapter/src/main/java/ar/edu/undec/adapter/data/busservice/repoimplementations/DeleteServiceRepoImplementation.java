package ar.edu.undec.adapter.data.busservice.repoimplementations;

import ar.edu.undec.adapter.data.busservice.crud.ServiceCRUD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import busservice.outputs.DeleteServiceRepository;

@AllArgsConstructor
@Service
public class DeleteServiceRepoImplementation implements DeleteServiceRepository {
    private ServiceCRUD serviceCRUD;

    @Override
    public boolean existsByName(String name) {
        return serviceCRUD.existsByName(name);
    }

    @Override
    public void deleteByName(String name) {
        serviceCRUD.deleteByName(name);
    }
}
