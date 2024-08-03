package ar.edu.undec.adapter.data.service.repoimplementations;

import ar.edu.undec.adapter.data.service.crud.ServiceCRUD;
import ar.edu.undec.adapter.data.service.mapper.ServiceDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import service.outputs.UpdateServiceRepository;

@AllArgsConstructor
@Service
public class UpdateServiceRepoImplementation implements UpdateServiceRepository {
    private ServiceCRUD serviceCRUD;

    @Override
    public boolean existsByName(String name) {
        return serviceCRUD.existsByName(name);
    }

    @Override
    public void update(service.models.Service service) {
        serviceCRUD.save(ServiceDataMapper.dataNodeMapper(service));
    }
}
