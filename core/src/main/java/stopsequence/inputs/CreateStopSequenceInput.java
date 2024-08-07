package stopsequence.inputs;

import route.models.RouteStatus;
import stopsequence.models.CreateStopSequenceRequestModel;

public interface CreateStopSequenceInput {
    RouteStatus createStopSequence(CreateStopSequenceRequestModel createStopSequenceRequestModel);
}
