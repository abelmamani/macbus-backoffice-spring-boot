package ar.edu.undec.adapter.data.busservice.repoimplementations;

import ar.edu.undec.adapter.data.busservice.crud.ServiceCRUD;
import ar.edu.undec.adapter.data.busservice.mapper.ServiceDataMapper;
import audit.EntityStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import busservice.outputs.UpdateServiceRepository;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UpdateServiceRepoImplementation implements UpdateServiceRepository {
    private ServiceCRUD serviceCRUD;

    @Override
    public boolean existsByName(String name) {
        return serviceCRUD.existsByName(name);
    }

    @Override
    public Optional<busservice.models.Service> findById(String id) {
        return serviceCRUD.findById(id).map(ServiceDataMapper::dataCoreMapper);
    }

    @Override
    public Optional<busservice.models.Service> findByName(String name) {
        return serviceCRUD.findByNameAndStatus(name, EntityStatus.ACTIVE).map(ServiceDataMapper::dataCoreMapper);
    }

    @Override
    public void update(busservice.models.Service service) {
        serviceCRUD.save(ServiceDataMapper.dataNodeMapper(service));
    }
}
