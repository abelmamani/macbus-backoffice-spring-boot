package busroute.usecases;

import audit.EntityStatus;
import busroute.exceptions.RouteAlreadyExistException;
import busroute.inputs.CreateRouteInput;
import busroute.models.CreateRouteRequestModel;
import busroute.models.Route;
import busroute.models.RouteProgressStatus;
import busroute.outputs.CreateRouteRepository;
import shape.models.Shape;
import stopsequence.models.StopSequence;
import trip.models.Trip;
import java.util.ArrayList;

public class CreateRouteUseCase implements CreateRouteInput {
    private CreateRouteRepository createRouteRepository;

    public CreateRouteUseCase(CreateRouteRepository createRouteRepository) {
        this.createRouteRepository = createRouteRepository;
    }

    @Override
    public String createRoute(CreateRouteRequestModel createRouteRequestModel) {
        if(createRouteRepository.existsByShortName(createRouteRequestModel.getShortName()))
            throw new RouteAlreadyExistException("La linea con nombre corto "+createRouteRequestModel.getShortName()+" ya existe.");
        if(createRouteRepository.existsByLongName(createRouteRequestModel.getLongName()))
            throw new RouteAlreadyExistException("La linea con nombre largo "+createRouteRequestModel.getLongName()+" ya existe.");

        Route route = Route.getInstance(null,
                createRouteRequestModel.getShortName(),
                createRouteRequestModel.getLongName(),
                createRouteRequestModel.getDescription(),
                createRouteRequestModel.getColor(),
                createRouteRequestModel.getTextColor(),
                RouteProgressStatus.EMPTY,
                EntityStatus.ACTIVE,
                new ArrayList<Shape>(),
                new ArrayList<StopSequence> (),
                new ArrayList<Trip>());
        return createRouteRepository.save(route);
    }
}
