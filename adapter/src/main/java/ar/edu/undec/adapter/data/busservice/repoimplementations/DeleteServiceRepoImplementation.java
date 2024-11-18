package ar.edu.undec.adapter.data.busservice.repoimplementations;

import ar.edu.undec.adapter.data.busservice.crud.ServiceCRUD;
import ar.edu.undec.adapter.data.busservice.mapper.ServiceDataMapper;
import audit.EntityStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import busservice.outputs.DeleteServiceRepository;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DeleteServiceRepoImplementation implements DeleteServiceRepository {
    private ServiceCRUD serviceCRUD;


    @Override
    public Optional<busservice.models.Service> findByNameAndStatus(String name, EntityStatus status) {
        return serviceCRUD.findByNameAndStatus(name, status).map(ServiceDataMapper::dataCoreMapper);
    }

    @Override
    public void update(busservice.models.Service service) {
        serviceCRUD.save(ServiceDataMapper.dataNodeMapper(service));
    }
}
