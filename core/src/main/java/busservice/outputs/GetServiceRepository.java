package busservice.outputs;

import busservice.models.Service;
import java.util.Optional;

public interface GetServiceRepository {
    Optional<Service> findByName(String name);
}
