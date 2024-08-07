package service.inputs;

import service.models.Service;

public interface GetServiceInput {
    Service getService(Long id);
    Service getServiceByName(String name);
}
