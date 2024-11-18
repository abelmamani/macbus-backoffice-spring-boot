package ar.edu.undec.adapter.data.busservice.repoimplementations;

import ar.edu.undec.adapter.data.busservice.crud.ServiceCRUD;
import ar.edu.undec.adapter.data.busservice.mapper.ServiceDataMapper;
import busservice.models.ServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import busservice.outputs.GetServicesRepository;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GetServicesRepoImplementation implements GetServicesRepository {
    private ServiceCRUD serviceCRUD;

    @Override
    public Collection<ServiceModel> findAll() {
        return serviceCRUD.findAllServices().stream().map(ServiceDataMapper::mapToServiceModel).collect(Collectors.toList());
    }

    @Override
    public Collection<ServiceModel> findAllActiveServices() {
        return serviceCRUD.findAllActiveServices().stream().map(ServiceDataMapper::mapToServiceModel).collect(Collectors.toList());
    }
}
