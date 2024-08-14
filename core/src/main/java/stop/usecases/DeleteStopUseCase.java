package stop.usecases;

import stop.exceptions.StopException;
import stop.exceptions.StopNotExistsException;
import stop.inputs.DeleteStopInput;
import stop.models.StopStatus;
import stop.outputs.DeleteStopRepository;

public class DeleteStopUseCase implements DeleteStopInput {
    private DeleteStopRepository deleteStopRepository;

    public DeleteStopUseCase(DeleteStopRepository deleteStopRepository) {
        this.deleteStopRepository = deleteStopRepository;
    }

    @Override
    public void deleteStop(String name) {
        if(!deleteStopRepository.existsByNameAndStatus(name, StopStatus.UNASSIGNED))
            throw new StopException( "La parada " + name + " no existe o no puede eliminarse.");
        deleteStopRepository.deleteByName(name);
    }
}
