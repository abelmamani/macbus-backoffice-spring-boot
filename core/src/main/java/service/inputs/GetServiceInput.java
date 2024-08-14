package service.inputs;

import service.models.Service;

public interface GetServiceInput {
    Service getService(String name);
}
