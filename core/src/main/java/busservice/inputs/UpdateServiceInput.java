package busservice.inputs;

import busservice.models.ServiceModel;

public interface UpdateServiceInput {
    void updateService(ServiceModel updateServiceRequestModel);
}
