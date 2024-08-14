package service.outputs;

import service.models.Service;
import java.util.Optional;

public interface UpdateServiceRepository {
    boolean existsByName(String name);
    Optional<Service> findByName(String name);
    void update(Service service);
}
