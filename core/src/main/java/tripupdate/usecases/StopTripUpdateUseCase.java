package tripupdate.usecases;

import tripupdate.exceptions.TripUpdateException;
import tripupdate.inputs.StopTripUpdateInput;
import tripupdate.outputs.TripUpdateRepository;

public class StopTripUpdateUseCase implements StopTripUpdateInput {
    private TripUpdateRepository tripUpdateRepository;

    public StopTripUpdateUseCase(TripUpdateRepository tripUpdateRepository) {
        this.tripUpdateRepository = tripUpdateRepository;
    }

    @Override
    public void stopTripUpdate(String tripId) {
        if (!tripUpdateRepository.isTripUpdateRunning(tripId))
            throw new TripUpdateException("No se puede detener el viaje, no han pasado 5 minutos desde la última actualización.");
        tripUpdateRepository.stopTripUpdate(tripId);
    }
}
