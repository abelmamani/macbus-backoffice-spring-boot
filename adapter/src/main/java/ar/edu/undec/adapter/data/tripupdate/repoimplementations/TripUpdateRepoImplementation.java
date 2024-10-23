package ar.edu.undec.adapter.data.tripupdate.repoimplementations;

import ar.edu.undec.adapter.data.tripupdate.crud.TripUpdateCRUD;
import ar.edu.undec.adapter.data.tripupdate.mapper.TripUpdateDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tripupdate.models.TripUpdateResponseModel;
import tripupdate.outputs.TripUpdateRepository;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TripUpdateRepoImplementation implements TripUpdateRepository {
    private TripUpdateCRUD tripUpdateCRUD;

    @Override
    public List<TripUpdateResponseModel> findTripUpdates() {
        return tripUpdateCRUD.findTripUpdates().stream()
                .map(TripUpdateDataMapper::mapToTripUpdateResponseModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isTripUpdateRunning(String id) {
        return tripUpdateCRUD.isTripUpdateRunning(id);
    }

    @Override
    public void stopTripUpdate(String id) {
        tripUpdateCRUD.stopTripUpdate(id);
    }
}
