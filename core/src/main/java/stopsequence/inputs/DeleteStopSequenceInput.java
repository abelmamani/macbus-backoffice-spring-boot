package stopsequence.inputs;

import busroute.models.RouteStatus;

public interface DeleteStopSequenceInput {
    RouteStatus deleteStopSequence(String busRouteName, String arrivalTime);
}
