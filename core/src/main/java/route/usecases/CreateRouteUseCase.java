package route.usecases;

import route.exceptions.RouteAlreadyExistException;
import route.inputs.CreateRouteInput;
import route.models.CreateRouteRequestModel;
import route.models.Route;
import route.models.RouteStatus;
import route.outputs.CreateRouteRepository;
import shape.models.Shape;
import stopsequence.models.StopSequence;
import trip.models.Trip;

import java.awt.*;
import java.util.ArrayList;

public class CreateRouteUseCase implements CreateRouteInput {
    private CreateRouteRepository createRouteRepository;

    public CreateRouteUseCase(CreateRouteRepository createRouteRepository) {
        this.createRouteRepository = createRouteRepository;
    }

    @Override
    public Long createRoute(CreateRouteRequestModel createRouteRequestModel) {
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
                RouteStatus.EMPTY,
                new ArrayList<Shape>(),
                new ArrayList<StopSequence> (),
                new ArrayList<Trip>());
        return createRouteRepository.save(route);
    }
}
