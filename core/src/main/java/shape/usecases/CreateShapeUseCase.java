package shape.usecases;

import busroute.exceptions.RouteNotExistsException;
import busroute.models.Route;
import busroute.models.RouteStatus;
import busroute.outputs.UpdateRouteRepository;
import shape.exceptions.ShapeException;
import shape.inpúts.CreateShapeInput;
import shape.models.CreateShapeRequestModel;

public class CreateShapeUseCase implements CreateShapeInput {
    private UpdateRouteRepository updateRouteRepository;

    public CreateShapeUseCase(UpdateRouteRepository updateRouteRepository) {
        this.updateRouteRepository = updateRouteRepository;
    }

    @Override
    public RouteStatus createShape(CreateShapeRequestModel createShapeRequestModel) {
        Route busRoute = updateRouteRepository.findByLongName(createShapeRequestModel.getRouteName())
                .orElseThrow(() -> new RouteNotExistsException("La linea a eliminar no existe."));
        RouteStatus routeStatus = busRoute.getRouteStatus();
        if(!routeStatus.equals(RouteStatus.EMPTY) && !routeStatus.equals(RouteStatus.WITH_SHAPES))
            throw new ShapeException("No se puede registrar el recorrido porque la ruta tiene paradas y/o viajes existentes.");
        Route route = Route.getInstance(busRoute.getId(),
                    busRoute.getShortName(),
                    busRoute.getLongName(),
                    busRoute.getDescription(),
                    busRoute.getColor(),
                    busRoute.getTextColor(),
                    RouteStatus.WITH_SHAPES,
                    createShapeRequestModel.getShapes(),
                    busRoute.getStopSequences(),
                    busRoute.getTrips());
        if(!busRoute.getRouteStatus().equals(RouteStatus.EMPTY)){
            updateRouteRepository.deleteShapesByLongName(createShapeRequestModel.getRouteName());
        }
        updateRouteRepository.update(route);
        return RouteStatus.WITH_SHAPES;
    }
}
