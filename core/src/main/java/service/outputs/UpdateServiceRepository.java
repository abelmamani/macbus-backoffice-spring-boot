package service.outputs;

import service.models.Service;

public interface UpdateServiceRepository {
    boolean existsByName(String name);
    void update(Service service);
}
