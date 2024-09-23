package trip.outputs;

import trip.models.Trip;
import trip.models.TripResponseModel;
import java.util.List;

public interface TripRepository {
    List<TripResponseModel> findAllByRouteLongName(String longName);
    boolean existsById(String id);
    boolean existsByRouteAndDepartureTimeAndServiceName(String longName, String departureTime, String serviceName);
    void addTrip(String longName, String tripId);
    String save(Trip trip);
}
