package busservice.inputs;

import busservice.models.Service;

public interface GetServiceInput {
    Service getService(String name);
}
