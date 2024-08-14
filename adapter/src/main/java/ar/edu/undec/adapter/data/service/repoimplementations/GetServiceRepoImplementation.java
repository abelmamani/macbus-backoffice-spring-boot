package ar.edu.undec.adapter.data.service.repoimplementations;

import ar.edu.undec.adapter.data.service.crud.ServiceCRUD;
import ar.edu.undec.adapter.data.service.mapper.ServiceDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import service.outputs.GetServiceRepository;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GetServiceRepoImplementation implements GetServiceRepository {
    private ServiceCRUD serviceCRUD;

    @Override
    public Optional<service.models.Service> findByName(String name) {
        return serviceCRUD.findByName(name).map(ServiceDataMapper::dataCoreMapper);
    }
}
