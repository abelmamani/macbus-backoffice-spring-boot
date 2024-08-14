package stop.usecases;

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
    public void updateStop(String name, UpdateStopRequestModel updateStopRequestModel) {
        Stop foundStop = updateStopRepository.findByName(name).orElseThrow(() -> new StopNotExistsException("La ´parda "+name+" no existe."));
        if(updateStopRepository.existsByName(updateStopRequestModel.getName()) && !foundStop.getName().equals(updateStopRequestModel.getName()))
            throw new StopAlreadyExistException("La parada con nombre "+ updateStopRequestModel.getName()+ " ya existe.");
        Stop newBusStop = Stop.getInstance(foundStop.getId(),
                updateStopRequestModel.getName(),
                updateStopRequestModel.getLatitude(),
                updateStopRequestModel.getLongitude(),
                foundStop.getStatus());
        updateStopRepository.update(newBusStop);
    }
}
