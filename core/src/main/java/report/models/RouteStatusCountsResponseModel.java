package report.models;

public class RouteStatusCountsResponseModel {
    private Long total;
    private Long empty;
    private Long withShapes;
    private Long withStop;
    private Long withStops;
    private Long withTrips;
    public RouteStatusCountsResponseModel(){}

    public RouteStatusCountsResponseModel(Long total, Long empty, Long withShapes, Long withStop, Long withStops, Long withTrips) {
        this.total = total;
        this.empty = empty;
        this.withShapes = withShapes;
        this.withStop = withStop;
        this.withStops = withStops;
        this.withTrips = withTrips;
    }

    public Long getTotal() {
        return total;
    }

    public Long getEmpty() {
        return empty;
    }

    public Long getWithShapes() {
        return withShapes;
    }

    public Long getWithStop() {
        return withStop;
    }

    public Long getWithStops() {
        return withStops;
    }

    public Long getWithTrips() {
        return withTrips;
    }
}
