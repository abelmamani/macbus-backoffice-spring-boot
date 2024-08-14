package service.inputs;

import service.models.UpdateServiceRequestModel;

public interface UpdateServiceInput {
    void updateService(String name, UpdateServiceRequestModel updateServiceRequestModel);
}
