package stop.usecases;

import stop.exceptions.StopNotExistsException;
import stop.inputs.DeleteStopInput;
import stop.outputs.DeleteStopRepository;

public class DeleteStopUseCase implements DeleteStopInput {
    private DeleteStopRepository deleteStopRepository;

    public DeleteStopUseCase(DeleteStopRepository deleteStopRepository) {
        this.deleteStopRepository = deleteStopRepository;
    }

    @Override
    public void deleteStop(Long id) {
        if(!deleteStopRepository.existsById(id))
            throw new StopNotExistsException("La parada con id " + id + " no existe.");
        deleteStopRepository.deleteById(id);
    }
}
