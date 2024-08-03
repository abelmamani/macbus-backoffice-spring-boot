package ar.edu.undec.adapter.data.service.repoimplementations;

import ar.edu.undec.adapter.data.service.crud.ServiceCRUD;
import ar.edu.undec.adapter.data.service.mapper.ServiceDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import service.outputs.GetServicesRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GetServicesRepoImplementation implements GetServicesRepository {
    private ServiceCRUD serviceCRUD;

    @Override
    public Collection<service.models.Service> findAll() {
        return serviceCRUD.findAll().stream().map(ServiceDataMapper::dataCoreMapper).collect(Collectors.toList());
    }
}
