package ar.edu.undec.adapter.data.trip.repoimplementations;

import ar.edu.undec.adapter.data.trip.crud.TripCRUD;
import ar.edu.undec.adapter.data.trip.mapper.TripDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trip.models.TripResponseModel;
import trip.outputs.TripRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GetTripsByRouteRepoImpelementation implements TripRepository {
    private final TripCRUD tripCRUD;

    @Override
    public List<TripResponseModel> findAllByRouteLongName(String longName) {
        List<Map<String, Object>> results = tripCRUD.findAllTripsByRouteLongName(longName);
        return results.stream()
                .map(TripDataMapper::mapToTripResponseModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByServiceName(String serviceName) {
        return tripCRUD.existsByServiceName(serviceName);
    }
}
