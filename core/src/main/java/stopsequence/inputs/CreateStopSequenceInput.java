package stopsequence.inputs;

import busroute.models.RouteProgressStatus;
import stopsequence.models.CreateStopSequenceRequestModel;

public interface CreateStopSequenceInput {
    RouteProgressStatus createStopSequence(CreateStopSequenceRequestModel createStopSequenceRequestModel);
}
