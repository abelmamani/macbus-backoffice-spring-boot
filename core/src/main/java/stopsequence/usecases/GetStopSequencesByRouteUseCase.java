package stopsequence.usecases;

import route.inputs.GetRouteInput;
import stopsequence.inputs.GetStopSequencesByRouteInput;
import stopsequence.models.StopSequence;

import java.util.List;

public class GetStopSequencesByRouteUseCase implements GetStopSequencesByRouteInput {
    private GetRouteInput getRouteInput;

    public GetStopSequencesByRouteUseCase(GetRouteInput getRouteInput) {
        this.getRouteInput = getRouteInput;
    }

    @Override
    public List<StopSequence> getAllStopSequences(String longName) {
        return getRouteInput.getRouteByName(longName).getStopSequences();
    }
}
