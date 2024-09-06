package ar.edu.undec.adapter.data.busservice.repoimplementations;

import ar.edu.undec.adapter.data.busservice.crud.ServiceCRUD;
import ar.edu.undec.adapter.data.busservice.mapper.ServiceDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import busservice.outputs.GetServiceRepository;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GetServiceRepoImplementation implements GetServiceRepository {
    private ServiceCRUD serviceCRUD;

    @Override
    public Optional<busservice.models.Service> findByName(String name) {
        return serviceCRUD.findByName(name).map(ServiceDataMapper::dataCoreMapper);
    }
}
