package busservice.usecases;

import busservice.exceptions.ServiceException;
import busservice.exceptions.ServiceNotExistsException;
import busservice.inputs.DeleteServiceInput;
import busservice.outputs.DeleteServiceRepository;
import trip.outputs.TripRepository;

public class DeleteServiceUseCase implements DeleteServiceInput {
    private DeleteServiceRepository deleteServiceRepository;
    private TripRepository tripRepository;

    public DeleteServiceUseCase(DeleteServiceRepository deleteServiceRepository, TripRepository tripRepository) {
        this.deleteServiceRepository = deleteServiceRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public void deleteServiceByName(String name) {
        if (!deleteServiceRepository.existsByName(name))
            throw new ServiceNotExistsException("El servicio " + name +" no existe.");
        if (tripRepository.existsByServiceName(name))
            throw new ServiceException("El servicio " + name + " está en uso por uno o más viajes y no puede ser eliminado.");
        deleteServiceRepository.deleteByName(name);
    }
}
