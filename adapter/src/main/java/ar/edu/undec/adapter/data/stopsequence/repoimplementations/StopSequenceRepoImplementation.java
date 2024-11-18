package ar.edu.undec.adapter.data.stopsequence.repoimplementations;

import ar.edu.undec.adapter.data.stopsequence.crud.StopSequenceCRUD;
import ar.edu.undec.adapter.data.stopsequence.mapper.StopSequenceDataMapper;
import busroute.models.RouteProgressStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stopsequence.models.StopSequence;
import stopsequence.models.StopSequenceResponseModel;
import stopsequence.outputs.StopSequenceRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StopSequenceRepoImplementation implements StopSequenceRepository {
    private StopSequenceCRUD stopSequenceCRUD;

    @Override
    public Optional<StopSequence> findById(String id) {
        return stopSequenceCRUD.findById(id).map(StopSequenceDataMapper::dataCoreMapper);
    }

    @Override
    public boolean existsByRouteAndArrivalTime(String longName, String arrivalTime) {
        return stopSequenceCRUD.existsByRouteAndArrivalTime(longName, arrivalTime);
    }

    @Override
    public boolean existsByStopName(String stopName) {
        return stopSequenceCRUD.existsByStopName(stopName);
    }

    @Override
    public int countStopSequencesByRoute(String longName) {
        return stopSequenceCRUD.countStopSequencesByRoute(longName);
    }

    @Override
    public void deleteStopSequence(String sequenceId) {
        stopSequenceCRUD.deleteStopSequenceB(sequenceId);
    }

    @Override
    public void addStopSequence(String longName, String sequenceId, RouteProgressStatus routeStatus) {
        stopSequenceCRUD.addStopSequence(longName, sequenceId, routeStatus);
    }

    @Override
    public String save(StopSequence stopSequence) {
        return stopSequenceCRUD.save(StopSequenceDataMapper.dataNodeMapper(stopSequence)).getId();
    }

    @Override
    public List<StopSequenceResponseModel> findAllByRouteLongName(String longName) {
        return stopSequenceCRUD.findAllStopSequencesByRouteLongName(longName).stream()
                .map(StopSequenceDataMapper::mapToStopSequenceResponseModel)
                .collect(Collectors.toList());
    }
}
