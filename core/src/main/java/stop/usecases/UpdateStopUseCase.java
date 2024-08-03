package stop.usecases;

import stop.exceptions.StopAlreadyExistException;
import stop.inputs.GetStopInput;
import stop.inputs.UpdateStopInput;
import stop.models.Stop;
import stop.models.UpdateStopRequestModel;
import stop.outputs.UpdateStopRepository;

public class UpdateStopUseCase implements UpdateStopInput {
    private UpdateStopRepository updateStopRepository;
    private GetStopInput getStopInput;

    public UpdateStopUseCase(UpdateStopRepository updateStopRepository, GetStopInput getStopInput) {
        this.updateStopRepository = updateStopRepository;
        this.getStopInput = getStopInput;
    }

    @Override
    public void updateStop(UpdateStopRequestModel updateStopRequestModel) {
        Stop foundStop= getStopInput.getStop(updateStopRequestModel.getId());
        if(updateStopRepository.existsByName(updateStopRequestModel.getName()) && !foundStop.getName().equals(updateStopRequestModel.getName()))
            throw new StopAlreadyExistException("La parada con nombre "+ updateStopRequestModel.getName()+ " ya existe.");
        Stop newBusStop = Stop.getInstance(updateStopRequestModel.getId(),
                updateStopRequestModel.getName(),
                updateStopRequestModel.getLatitude(),
                updateStopRequestModel.getLongitude());
        updateStopRepository.update(newBusStop);
    }
}
