package ar.edu.undec.adapter.data.stopsequence.repoimplementations;

import ar.edu.undec.adapter.data.stopsequence.crud.StopSequenceCRUD;
import ar.edu.undec.adapter.data.stopsequence.mapper.StopSequenceDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stopsequence.models.StopSequenceResponseModel;
import stopsequence.outputs.StopSequenceRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GetStopSequenceRepoImplementation implements StopSequenceRepository {
    private StopSequenceCRUD stopSequenceCRUD;

    @Override
    public boolean isStopUsedInOtherRoutes(String stopName, String routeName) {
        return stopSequenceCRUD.isStopUsedInOtherRoutes(stopName, routeName);
    }

    @Override
    public List<StopSequenceResponseModel> findAllByRouteLongName(String longName) {
        List<Map<String, Object>> results = stopSequenceCRUD.findAllStopSequencesByRouteLongName(longName);
        return results.stream()
                .map(StopSequenceDataMapper::mapToStopSequenceResponseModel)
                .collect(Collectors.toList());
    }
}
