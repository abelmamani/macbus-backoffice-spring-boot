package stop.inputs;

import stop.models.CreateStopRequestModel;
import stop.models.Stop;

public interface CreateStopInput {
    String createStop(CreateStopRequestModel createStopRequestModel);
}
