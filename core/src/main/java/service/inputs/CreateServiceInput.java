package service.inputs;

import service.models.CreateServiceRequestModel;

public interface CreateServiceInput {
    Long createService(CreateServiceRequestModel createServiceRequestModel);
}
