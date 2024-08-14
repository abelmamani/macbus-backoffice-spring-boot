package service.outputs;

import service.models.Service;
import java.util.Optional;

public interface GetServiceRepository {
    Optional<Service> findByName(String name);
}
