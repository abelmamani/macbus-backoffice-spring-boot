package service.outputs;

import service.models.Service;
import java.util.Optional;

public interface GetServiceRepository {
    Optional<Service> findById(Long id);
    Optional<Service> findByName(String name);
}
