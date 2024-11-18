package busservice.outputs;

import audit.EntityStatus;
import busservice.models.Service;
import java.util.Optional;

public interface UpdateServiceRepository {
    boolean existsByName(String name);
    Optional<Service> findById(String id);
    Optional<Service> findByName(String name);
    void update(Service service);
}
