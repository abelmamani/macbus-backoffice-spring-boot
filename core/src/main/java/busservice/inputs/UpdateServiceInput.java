package busservice.inputs;

import busservice.models.ServiceModel;

public interface UpdateServiceInput {
    void updateService(String name, ServiceModel updateServiceRequestModel);
}
