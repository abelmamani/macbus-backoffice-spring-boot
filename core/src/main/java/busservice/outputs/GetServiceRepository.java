package busservice.outputs;

import audit.EntityStatus;
import busservice.models.Service;
import java.util.Optional;

public interface GetServiceRepository {
    Optional<Service> findByNameAndStatus(String name, EntityStatus status);
}
