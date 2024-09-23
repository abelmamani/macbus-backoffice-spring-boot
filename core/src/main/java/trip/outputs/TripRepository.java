package trip.outputs;

import trip.models.Trip;
import trip.models.TripResponseModel;
import java.util.List;

public interface TripRepository {

    boolean existsById(String id);
    boolean existsByServiceName(String serviceName);
    boolean existsByRouteAndDepartureTimeAndServiceName(String longName, String departureTime, String serviceName);
    int countTripsByRoute(String longName);
    void deleteTripAndStopTimes(String tripId);
    void addTrip(String longName, String tripId);
    String save(Trip trip);
    List<TripResponseModel> findAllByRouteLongName(String longName);
}
