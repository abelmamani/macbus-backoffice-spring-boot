package stopsequence.inputs;

import busroute.models.RouteProgressStatus;

public interface DeleteStopSequenceInput {
    RouteProgressStatus deleteStopSequence(String busRouteName, String sequenceId);
}
