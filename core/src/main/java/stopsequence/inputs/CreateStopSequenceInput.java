package stopsequence.inputs;

import busroute.models.RouteStatus;
import stopsequence.models.CreateStopSequenceRequestModel;

public interface CreateStopSequenceInput {
    RouteStatus createStopSequence(CreateStopSequenceRequestModel createStopSequenceRequestModel);
}
