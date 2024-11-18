package busservice.usecases;

import audit.EntityStatus;
import busservice.exceptions.ServiceException;
import busservice.exceptions.ServiceNotExistsException;
import busservice.inputs.DeleteServiceInput;
import busservice.models.Service;
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
        Service findService = deleteServiceRepository.findByNameAndStatus(name, EntityStatus.ACTIVE).orElseThrow(() -> new ServiceNotExistsException("El servicio " + name +" no existe."));
        if(findService.getCalendarDates().size() > 0)
            throw new ServiceException("El servicio " + name + " está en uso por uno o más fechas de calendario.");
        if (tripRepository.existsByServiceName(name))
            throw new ServiceException("El servicio " + name + " está en uso por uno o más viajes.");
        Service service = Service.getInstance(
                findService.getId(),
                findService.getName(),
                findService.getStartDate(),
                findService.getEndDate(),
                EntityStatus.INACTIVE,
                findService.getCalendarDates());
        deleteServiceRepository.update(service);
    }
}
