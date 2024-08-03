package route.usecases;

import route.exceptions.RouteAlreadyExistException;
import route.inputs.GetRouteInput;
import route.inputs.UpdateGeneralInfoInput;
import route.models.Route;
import route.models.UpdateRouteRequestModel;
import route.outputs.UpdateGeneralInfoRepository;

public class UpdateGeneralInfoUseCase implements UpdateGeneralInfoInput {
    private UpdateGeneralInfoRepository updateRouteRepository;
    private GetRouteInput getRouteInput;

    public UpdateGeneralInfoUseCase(UpdateGeneralInfoRepository updateRouteRepository, GetRouteInput getRouteInput) {
        this.updateRouteRepository = updateRouteRepository;
        this.getRouteInput = getRouteInput;
    }

    @Override
    public void updateRoute(UpdateRouteRequestModel updateRouteRequestModel) {
        Route foundRoute = getRouteInput.getRouteById(updateRouteRequestModel.getId());
        if(updateRouteRepository.existsByShortName(updateRouteRequestModel.getShortName()) && !foundRoute.getShortName().equals(updateRouteRequestModel.getShortName()))
            throw new RouteAlreadyExistException("La linea con nombre corto " + updateRouteRequestModel.getShortName() + " ya existe.");
        if(updateRouteRepository.existsByLongName(updateRouteRequestModel.getLongName()) && !foundRoute.getLongName().equals(updateRouteRequestModel.getLongName()))
            throw new RouteAlreadyExistException("La linea con nombre largo " + updateRouteRequestModel.getLongName() + " ya existe.");
        Route route = Route.getInstance(updateRouteRequestModel.getId(),
              updateRouteRequestModel.getShortName(),
              updateRouteRequestModel.getLongName(),
              updateRouteRequestModel.getDescription(),
              updateRouteRequestModel.getColor(),
              updateRouteRequestModel.getTextColor(),
              foundRoute.getRouteStatus(),
                foundRoute.getShapes(),
                foundRoute.getStopSequences(),
                foundRoute.getTrips());
        updateRouteRepository.update(route);
    }
}
