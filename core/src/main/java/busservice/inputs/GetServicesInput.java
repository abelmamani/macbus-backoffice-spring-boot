package busservice.inputs;

import busservice.models.ServiceModel;
import java.util.Collection;

public interface GetServicesInput {
    Collection<ServiceModel> getServices();
}
