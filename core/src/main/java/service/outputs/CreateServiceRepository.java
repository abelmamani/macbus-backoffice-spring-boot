package service.outputs;

import service.models.Service;

public interface CreateServiceRepository {
    boolean existsByName(String name);
    String save(Service service);
}
