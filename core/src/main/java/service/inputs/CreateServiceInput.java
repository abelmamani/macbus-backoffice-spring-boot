package service.inputs;

import service.models.CreateServiceRequestModel;

public interface CreateServiceInput {
    String createService(CreateServiceRequestModel createServiceRequestModel);
}
