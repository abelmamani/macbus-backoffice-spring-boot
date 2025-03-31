package stop.usecases;

import audit.EntityStatus;
import stop.exceptions.StopAlreadyExistException;
import stop.inputs.CreateStopInput;
import stop.models.CreateStopRequestModel;
import stop.models.Stop;
import stop.models.StopAssignedStatus;
import stop.outputs.CreateStopRepository;

public class CreateStopUseCase implements CreateStopInput {
    private CreateStopRepository createStopRepository;

    public CreateStopUseCase(CreateStopRepository createStopRepository) {
        this.createStopRepository = createStopRepository;
    }

    @Override
    public String createStop(CreateStopRequestModel createStopRequestModel) {
        if(createStopRepository.existsByName(createStopRequestModel.getName().trim()))
            throw new StopAlreadyExistException("La parada con nombre "+createStopRequestModel.getName()+" ya existe.");
        Stop stop = Stop.getInstance(null,
                createStopRequestModel.getName(),
                createStopRequestModel.getLatitude(),
                createStopRequestModel.getLongitude(),
                StopAssignedStatus.UNASSIGNED,
                EntityStatus.ACTIVE);
        return createStopRepository.save(stop);
    }
}
