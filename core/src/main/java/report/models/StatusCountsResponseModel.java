package report.models;
public class StatusCountsResponseModel {
    RouteStatusCountsResponseModel routeStatusCounts;
    StopStatusCountsResponseModel stopStatusCounts;
    TripStatusCountsResponseModel tripStatusCounts;
    public StatusCountsResponseModel(){}
    public StatusCountsResponseModel(RouteStatusCountsResponseModel routeStatusCounts, StopStatusCountsResponseModel stopStatusCounts, TripStatusCountsResponseModel tripStatusCounts) {
        this.routeStatusCounts = routeStatusCounts;
        this.stopStatusCounts = stopStatusCounts;
        this.tripStatusCounts = tripStatusCounts;
    }

    public RouteStatusCountsResponseModel getRouteStatusCounts() {
        return routeStatusCounts;
    }

    public StopStatusCountsResponseModel getStopStatusCounts() {
        return stopStatusCounts;
    }

    public TripStatusCountsResponseModel getTripStatusCounts() {
        return tripStatusCounts;
    }
}
