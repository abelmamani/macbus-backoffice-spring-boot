package ar.edu.undec.adapter.data.trip.repoimplementations;

import ar.edu.undec.adapter.data.trip.crud.TripCRUD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trip.models.TripResponseModel;
import trip.outputs.TripRepository;

import java.time.LocalTime;
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
                .map(this::mapToTripResponseModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByServiceName(String serviceName) {
        return tripCRUD.existsByServiceName(serviceName);
    }

    private TripResponseModel mapToTripResponseModel(Map<String, Object> map) {
        return new TripResponseModel(
                (LocalTime) map.get("departureTime"),
                (String) map.get("tripStatus"),
                (String) map.get("service")
        );
    }
}
