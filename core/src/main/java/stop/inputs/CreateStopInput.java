package stop.inputs;

import stop.models.CreateStopRequestModel;

public interface CreateStopInput {
    Long createStop(CreateStopRequestModel createStopRequestModel);
}
