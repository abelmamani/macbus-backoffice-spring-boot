package route.usecases;

import route.exceptions.RouteAlreadyExistException;
import route.exceptions.RouteNotExistsException;
import route.inputs.GetRouteInput;
import route.inputs.UpdateGeneralInfoInput;
import route.models.Route;
import route.models.UpdateRouteRequestModel;
import route.outputs.UpdateGeneralInfoRepository;

public class UpdateGeneralInfoUseCase implements UpdateGeneralInfoInput {
    private UpdateGeneralInfoRepository updateGeneralInfoRepository;

    public UpdateGeneralInfoUseCase(UpdateGeneralInfoRepository updateGeneralInfoRepository) {
        this.updateGeneralInfoRepository = updateGeneralInfoRepository;
    }

    @Override
    public void updateRoute(String longName, UpdateRouteRequestModel updateRouteRequestModel) {
        Route foundRoute = updateGeneralInfoRepository.findByLongName(longName)
                .orElseThrow(()-> new RouteNotExistsException("La lines "+longName+" no existe."));
        if(updateGeneralInfoRepository.existsByShortName(updateRouteRequestModel.getShortName()) && !foundRoute.getShortName().equals(updateRouteRequestModel.getShortName()))
            throw new RouteAlreadyExistException("La linea con nombre corto " + updateRouteRequestModel.getShortName() + " ya existe.");
        if(updateGeneralInfoRepository.existsByLongName(updateRouteRequestModel.getLongName()) && !foundRoute.getLongName().equals(updateRouteRequestModel.getLongName()))
            throw new RouteAlreadyExistException("La linea con nombre largo " + updateRouteRequestModel.getLongName() + " ya existe.");
        Route route = Route.getInstance(foundRoute.getId(),
              updateRouteRequestModel.getShortName(),
              updateRouteRequestModel.getLongName(),
              updateRouteRequestModel.getDescription(),
              updateRouteRequestModel.getColor(),
              updateRouteRequestModel.getTextColor(),
              foundRoute.getRouteStatus(),
                foundRoute.getShapes(),
                foundRoute.getStopSequences(),
                foundRoute.getTrips());
        updateGeneralInfoRepository.update(route);
    }
}
