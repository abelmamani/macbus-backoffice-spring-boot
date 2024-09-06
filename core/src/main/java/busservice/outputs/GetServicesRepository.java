package busservice.outputs;

import busservice.models.ServiceModel;
import java.util.Collection;

public interface GetServicesRepository {
    Collection<ServiceModel> findAll();
}
