package shape.usecases;

import route.inputs.GetRouteInput;
import route.inputs.UpdateRouteInput;
import route.models.Route;
import route.models.RouteStatus;
import shape.inpúts.CreateShapeInput;
import shape.models.CreateShapeRequestModel;

public class CreateShapeUseCase implements CreateShapeInput {
    private GetRouteInput getRouteInput;
    private UpdateRouteInput updateRouteInput;

    public CreateShapeUseCase(GetRouteInput getRouteInput, UpdateRouteInput updateRouteInput) {
        this.getRouteInput = getRouteInput;
        this.updateRouteInput = updateRouteInput;
    }

    @Override
    public RouteStatus createShape(CreateShapeRequestModel createShapeRequestModel) {
        Route busRoute = getRouteInput.getRouteById(createShapeRequestModel.getRouteId());
        Route route = Route.getInstance(busRoute.getId(),
                    busRoute.getShortName(),
                    busRoute.getLongName(),
                    busRoute.getDescription(),
                    busRoute.getColor(),
                    busRoute.getTextColor(),
                    busRoute.getRouteStatus().equals(RouteStatus.EMPTY) ? RouteStatus.WITH_SHAPES : busRoute.getRouteStatus(),
                    createShapeRequestModel.getShapes(),
                    busRoute.getStopSequences(),
                    busRoute.getTrips());
        updateRouteInput.updateRoute(route);
        return busRoute.getRouteStatus().equals(RouteStatus.EMPTY) ? RouteStatus.WITH_SHAPES : busRoute.getRouteStatus();
    }
}
