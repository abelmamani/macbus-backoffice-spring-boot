package busservice.outputs;

import busservice.models.Service;

public interface CreateServiceRepository {
    boolean existsByName(String name);
    String save(Service service);
}
