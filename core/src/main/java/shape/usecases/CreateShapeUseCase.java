package shape.usecases;

import audit.EntityStatus;
import busroute.exceptions.RouteNotExistsException;
import busroute.models.Route;
import busroute.models.RouteProgressStatus;
import busroute.outputs.UpdateRouteRepository;
import shape.exceptions.ShapeException;
import shape.inpúts.CreateShapeInput;
import shape.models.CreateShapeRequestModel;
import shape.outputs.ShapeRepository;

public class CreateShapeUseCase implements CreateShapeInput {
    private UpdateRouteRepository updateRouteRepository;
    private ShapeRepository shapeRepository;

    public CreateShapeUseCase(UpdateRouteRepository updateRouteRepository, ShapeRepository shapeRepository) {
        this.updateRouteRepository = updateRouteRepository;
        this.shapeRepository = shapeRepository;
    }

    @Override
    public RouteProgressStatus createShape(CreateShapeRequestModel createShapeRequestModel) {
        Route busRoute = updateRouteRepository.findByLongName(createShapeRequestModel.getRouteName())
                .orElseThrow(() -> new RouteNotExistsException("La linea no existe."));
        RouteProgressStatus progressStatus = busRoute.getProgressStatus();
        if(!progressStatus.equals(RouteProgressStatus.EMPTY) && !progressStatus.equals(RouteProgressStatus.WITH_SHAPES))
            throw new ShapeException("No se puede registrar el recorrido porque la ruta tiene paradas y/o viajes existentes.");
        Route route = Route.getInstance(busRoute.getId(),
                    busRoute.getShortName(),
                    busRoute.getLongName(),
                    busRoute.getDescription(),
                    busRoute.getColor(),
                    busRoute.getTextColor(),
                    RouteProgressStatus.WITH_SHAPES,
                    busRoute.getStatus(),
                    createShapeRequestModel.getShapes(),
                    busRoute.getStopSequences(),
                    busRoute.getTrips());

        if(!progressStatus.equals(RouteProgressStatus.EMPTY)){
            shapeRepository.deleteShapesByRoute(createShapeRequestModel.getRouteName());
        }

        updateRouteRepository.update(route);
        return RouteProgressStatus.WITH_SHAPES;
    }
}
