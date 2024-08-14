package trip.outputs;

import trip.models.TripResponseModel;
import java.util.List;

public interface TripRepository {
    List<TripResponseModel> findAllByRouteLongName(String longName);
    boolean existsByServiceName(String serviceName);
}
