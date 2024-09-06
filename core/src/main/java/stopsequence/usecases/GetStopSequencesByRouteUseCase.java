package stopsequence.usecases;

import stopsequence.inputs.GetStopSequencesByRouteInput;
import stopsequence.models.StopSequenceResponseModel;
import stopsequence.outputs.StopSequenceRepository;
import java.util.List;

public class GetStopSequencesByRouteUseCase implements GetStopSequencesByRouteInput {
    private StopSequenceRepository stopSequenceRepository;

    public GetStopSequencesByRouteUseCase(StopSequenceRepository stopSequenceRepository) {
        this.stopSequenceRepository = stopSequenceRepository;
    }

    @Override
    public List<StopSequenceResponseModel> getAllStopSequences(String longName) {
        return stopSequenceRepository.findAllByRouteLongName(longName);
    }
}
