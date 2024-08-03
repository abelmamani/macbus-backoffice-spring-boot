package ar.edu.undec.adapter.data.service.repoimplementations;

import ar.edu.undec.adapter.data.service.crud.ServiceCRUD;
import ar.edu.undec.adapter.data.service.mapper.ServiceDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import service.outputs.CreateServiceRepository;

@AllArgsConstructor
@Service
public class CreateServiceRepoImplementation implements CreateServiceRepository {
    private ServiceCRUD serviceCRUD;

    @Override
    public boolean existsByName(String name) {
        return serviceCRUD.existsByName(name);
    }

    @Override
    public Long save(service.models.Service service) {
        return serviceCRUD.save(ServiceDataMapper.dataNodeMapper(service)).getId();
    }
}
