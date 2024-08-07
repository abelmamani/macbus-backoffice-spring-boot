package trip.outputs;

import trip.models.Trip;

import java.util.List;

public interface GetTripsByRouteRepository {
    List<Trip> findAllByRouteLongName(String longName);
}
