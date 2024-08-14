package stopsequence.inputs;

import route.models.RouteStatus;

import java.time.LocalTime;

public interface DeleteStopSequenceInput {
    RouteStatus deleteStopSequence(String busRouteName, LocalTime arrivalTime);
}
