package service.outputs;

import service.models.Service;
import java.util.Collection;

public interface GetServicesRepository {
    Collection<Service> findAll();
}
