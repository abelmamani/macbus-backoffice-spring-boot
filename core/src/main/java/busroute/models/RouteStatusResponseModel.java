package busroute.models;

public class RouteStatusResponseModel {
    private RouteStatus routeStatus;
    public RouteStatusResponseModel(){}

    public RouteStatusResponseModel(RouteStatus routeStatus) {
        this.routeStatus = routeStatus;
    }

    public RouteStatus getRouteStatus() {
        return routeStatus;
    }
}
