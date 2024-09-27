package report.usecases;

import busroute.outputs.GetRouteRepository;
import report.inputs.GetStatusCountsInput;
import report.models.RouteStatusCountsResponseModel;
import report.models.StatusCountsResponseModel;
import report.models.StopStatusCountsResponseModel;
import report.models.TripStatusCountsResponseModel;
import stop.outputs.GetStopRepository;
import trip.outputs.TripRepository;

public class GetSatusCuntsUseCase implements GetStatusCountsInput {
    private GetRouteRepository getRouteRepository;
    private GetStopRepository getStopRepository;
    private TripRepository tripRepository;

    public GetSatusCuntsUseCase(GetRouteRepository getRouteRepository, GetStopRepository getStopRepository, TripRepository tripRepository) {
        this.getRouteRepository = getRouteRepository;
        this.getStopRepository = getStopRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public StatusCountsResponseModel getStatusCounts() {
        RouteStatusCountsResponseModel routeStatusCounts = getRouteRepository.getRouteStatusCounts();
        StopStatusCountsResponseModel stopStatusCounts = getStopRepository.getStopStatusCounts();
        TripStatusCountsResponseModel tripStatusCounts = tripRepository.getTripStatusCounts();
        return new StatusCountsResponseModel(routeStatusCounts, stopStatusCounts, tripStatusCounts);
    }
}
