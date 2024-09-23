package ar.edu.undec.adapter.data.trip.repoimplementations;

import ar.edu.undec.adapter.data.trip.crud.TripCRUD;
import ar.edu.undec.adapter.data.trip.mapper.TripDataMapper;
import busroute.models.RouteStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trip.models.Trip;
import trip.models.TripResponseModel;
import trip.outputs.TripRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TripRepoImpelementation implements TripRepository {
    private final TripCRUD tripCRUD;

    @Override
    public boolean existsById(String id) {
        return tripCRUD.existsById(id);
    }

    @Override
    public boolean existsByServiceName(String serviceName) {
        return tripCRUD.existsByServiceName(serviceName);
    }

    @Override
    public boolean existsByRouteAndDepartureTimeAndServiceName(String longName, String departureTime, String serviceName) {
        return tripCRUD.existsByRouteAndDepartureTimeAndService(longName, departureTime, serviceName);
    }

    @Override
    public int countTripsByRoute(String longName) {
        return tripCRUD.countTripsByRoute(longName);
    }

    @Override
    public void deleteTripAndStopTimes(String tripId) {
        tripCRUD.deleteTripAndStopTimes(tripId);
    }

    @Override
    public void addTrip(String longName, String tripId) {
        tripCRUD.addTrip(longName, tripId, RouteStatus.WITH_TRIPS);
    }

    @Override
    public String save(Trip trip) {
        return tripCRUD.save(TripDataMapper.dataNodeMapper(trip)).getId();
    }
    @Override
    public List<TripResponseModel> findAllByRouteLongName(String longName) {
        List<Map<String, Object>> results = tripCRUD.findAllTripsByRouteLongName(longName);
        return results.stream()
                .map(TripDataMapper::mapToTripResponseModel)
                .collect(Collectors.toList());
    }
}
