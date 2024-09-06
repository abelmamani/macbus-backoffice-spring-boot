package busservice.inputs;

import busservice.models.ServiceModel;

public interface CreateServiceInput {
    String createService(ServiceModel createServiceRequestModel);
}
