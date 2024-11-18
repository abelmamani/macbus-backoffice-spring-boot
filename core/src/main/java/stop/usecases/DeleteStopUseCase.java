package stop.usecases;

import audit.EntityStatus;
import stop.exceptions.StopException;
import stop.inputs.DeleteStopInput;
import stop.models.Stop;
import stop.models.StopAssignedStatus;
import stop.outputs.DeleteStopRepository;

public class DeleteStopUseCase implements DeleteStopInput {
    private DeleteStopRepository deleteStopRepository;

    public DeleteStopUseCase(DeleteStopRepository deleteStopRepository) {
        this.deleteStopRepository = deleteStopRepository;
    }

    @Override
    public void deleteStop(String id) {
        Stop findStop = deleteStopRepository.findById(id).orElseThrow(() -> new StopException( "La parada a eliminar no existe."));
        if(findStop.getStatus().equals(EntityStatus.INACTIVE))
            throw new StopException("La parada ya se encuentra dado de baja.");
        if(findStop.getAssignedStatus().equals(StopAssignedStatus.ASSIGNED))
            throw new StopException("La parada no se puede eliminar porque esta en uso.");
        Stop stop = Stop.getInstance(findStop.getId(),
                findStop.getName(),
                findStop.getLatitude(),
                findStop.getLongitude(),
                findStop.getAssignedStatus(),
                EntityStatus.INACTIVE);
        deleteStopRepository.save(stop);
    }
}
