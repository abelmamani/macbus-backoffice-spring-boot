package stop.inputs;

import stop.models.UpdateStopRequestModel;

public interface UpdateStopInput {
    void updateStop(String name, UpdateStopRequestModel updateStopRequestModel);
}
