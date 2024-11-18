package busroute.models;

public class RouteStatusResponseModel {
    private RouteProgressStatus routeStatus;
    public RouteStatusResponseModel(){}

    public RouteStatusResponseModel(RouteProgressStatus routeStatus) {
        this.routeStatus = routeStatus;
    }

    public RouteProgressStatus getRouteStatus() {
        return routeStatus;
    }
}
