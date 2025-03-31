package stop.usecases;

import audit.EntityStatus;
import role.exceptions.RoleException;
import stop.exceptions.StopAlreadyExistException;
import stop.exceptions.StopNotExistsException;
import stop.inputs.UpdateStopInput;
import stop.models.Stop;
import stop.models.UpdateStopRequestModel;
import stop.outputs.UpdateStopRepository;

public class UpdateStopUseCase implements UpdateStopInput {
    private UpdateStopRepository updateStopRepository;

    public UpdateStopUseCase(UpdateStopRepository updateStopRepository) {
        this.updateStopRepository = updateStopRepository;
    }

    @Override
    public void updateStop(UpdateStopRequestModel updateStopRequestModel) {
        Stop findStop = updateStopRepository.findById(updateStopRequestModel.getId()).orElseThrow(() -> new StopNotExistsException("La parada a actualizar no existe."));
        EntityStatus newStatus = findStop.getStatus();
        if (newStatus.equals(EntityStatus.INACTIVE)) {
            validateStringField(updateStopRequestModel.getStatus(), "estado del servicio");
            try {
                newStatus = EntityStatus.valueOf(updateStopRequestModel.getStatus());
            } catch (IllegalArgumentException e) {
                throw new RoleException("El estado " + updateStopRequestModel.getStatus()+ " no es válido.");
            }
        }
        if(updateStopRepository.existsByName(updateStopRequestModel.getName().trim()) && !findStop.getName().equals(updateStopRequestModel.getName().trim()))
            throw new StopAlreadyExistException("La parada con nombre "+ updateStopRequestModel.getName()+ " ya existe.");
        Stop newBusStop = Stop.getInstance(
                findStop.getId(),
                updateStopRequestModel.getName(),
                updateStopRequestModel.getLatitude(),
                updateStopRequestModel.getLongitude(),
                findStop.getAssignedStatus(),
                newStatus);
        updateStopRepository.update(newBusStop);
    }

    private void validateStringField(String field, String fieldName) {
        if (field == null || field.isBlank()) {
            throw new RoleException("El " + fieldName + " es requerido.");
        }
    }
}
