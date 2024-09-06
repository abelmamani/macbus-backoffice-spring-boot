package ar.edu.undec.adapter.data.busservice.repoimplementations;

import ar.edu.undec.adapter.data.busservice.crud.ServiceCRUD;
import ar.edu.undec.adapter.data.busservice.mapper.ServiceDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import busservice.outputs.CreateServiceRepository;

@AllArgsConstructor
@Service
public class CreateServiceRepoImplementation implements CreateServiceRepository {
    private ServiceCRUD serviceCRUD;

    @Override
    public boolean existsByName(String name) {
        return serviceCRUD.existsByName(name);
    }

    @Override
    public String save(busservice.models.Service service) {
        return serviceCRUD.save(ServiceDataMapper.dataNodeMapper(service)).getId();
    }
}
