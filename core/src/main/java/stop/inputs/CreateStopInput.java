package stop.inputs;

import stop.models.CreateStopRequestModel;
import stop.models.Stop;

public interface CreateStopInput {
    Stop createStop(CreateStopRequestModel createStopRequestModel);
}
