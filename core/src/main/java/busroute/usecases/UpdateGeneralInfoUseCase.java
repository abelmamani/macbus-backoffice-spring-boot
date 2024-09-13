package busroute.usecases;

import busroute.exceptions.RouteAlreadyExistException;
import busroute.exceptions.RouteNotExistsException;
import busroute.inputs.UpdateGeneralInfoInput;
import busroute.models.UpdateRouteRequestModel;
import busroute.outputs.UpdateGeneralInfoRepository;

public class UpdateGeneralInfoUseCase implements UpdateGeneralInfoInput {
    private UpdateGeneralInfoRepository updateGeneralInfoRepository;

    public UpdateGeneralInfoUseCase(UpdateGeneralInfoRepository updateGeneralInfoRepository) {
        this.updateGeneralInfoRepository = updateGeneralInfoRepository;
    }

    @Override
    public void updateRoute(String longName, UpdateRouteRequestModel updateRouteRequestModel) {
        if(!updateGeneralInfoRepository.existsByLongName(longName))
            throw new RouteNotExistsException("La línea " + longName + " no existe.");
        if (updateGeneralInfoRepository.existsByShortNameAndNotLongName(updateRouteRequestModel.getShortName(), longName)) {
            throw new RouteAlreadyExistException("La línea con nombre corto " + updateRouteRequestModel.getShortName() + " ya existe.");
        }  if(!longName.equals(updateRouteRequestModel.getLongName()) && updateGeneralInfoRepository.existsByLongName(updateRouteRequestModel.getLongName()))
            throw new RouteAlreadyExistException("La linea con nombre largo " + updateRouteRequestModel.getLongName() + " ya existe.");
        UpdateRouteRequestModel updateRoute = UpdateRouteRequestModel.getInstance(updateRouteRequestModel.getShortName(),
                updateRouteRequestModel.getLongName(),
                updateRouteRequestModel.getDescription(),
                updateRouteRequestModel.getColor(),
                updateRouteRequestModel.getTextColor());
        updateGeneralInfoRepository.update(longName, updateRoute);
    }
}
