package busroute.usecases;

import audit.EntityStatus;
import busroute.exceptions.RouteAlreadyExistException;
import busroute.exceptions.RouteNotExistsException;
import busroute.inputs.UpdateGeneralInfoInput;
import busroute.models.UpdateRouteRequestModel;
import busroute.outputs.UpdateGeneralInfoRepository;
import role.exceptions.RoleException;

import java.util.Optional;

public class UpdateGeneralInfoUseCase implements UpdateGeneralInfoInput {
    private UpdateGeneralInfoRepository updateGeneralInfoRepository;

    public UpdateGeneralInfoUseCase(UpdateGeneralInfoRepository updateGeneralInfoRepository) {
        this.updateGeneralInfoRepository = updateGeneralInfoRepository;
    }


    @Override
    public void updateRoute(String longName, UpdateRouteRequestModel updateRouteRequestModel) {
        EntityStatus newStatus = updateGeneralInfoRepository.getStatusByLongName(longName).orElseThrow(
                () -> new RouteNotExistsException("La línea " + longName + " no existe."));
        if (newStatus.equals(EntityStatus.INACTIVE)) {
            validateStringField(updateRouteRequestModel.getStatus(), "estado de la linea");
            try {
                newStatus = EntityStatus.valueOf(updateRouteRequestModel.getStatus());
            } catch (IllegalArgumentException e) {
                throw new RoleException("El estado " + updateRouteRequestModel.getStatus()+ " no es válido.");
            }
        }

        if (updateGeneralInfoRepository.existsByShortNameAndNotLongName(updateRouteRequestModel.getShortName().trim(), longName))
            throw new RouteAlreadyExistException("La línea con nombre corto " + updateRouteRequestModel.getShortName() + " ya existe.");

        if(!longName.equals(updateRouteRequestModel.getLongName().trim()) && updateGeneralInfoRepository.existsByLongName(updateRouteRequestModel.getLongName().trim()))
            throw new RouteAlreadyExistException("La linea con nombre largo " + updateRouteRequestModel.getLongName() + " ya existe.");
        UpdateRouteRequestModel updateRoute = UpdateRouteRequestModel.getInstance(updateRouteRequestModel.getShortName(),
                updateRouteRequestModel.getLongName(),
                updateRouteRequestModel.getDescription(),
                updateRouteRequestModel.getColor(),
                updateRouteRequestModel.getTextColor(),
                newStatus.toString());
        updateGeneralInfoRepository.update(longName, updateRoute);
    }

    private void validateStringField(String field, String fieldName) {
        if (field == null || field.isBlank()) {
            throw new RoleException("El " + fieldName + " es requerido.");
        }
    }
}
