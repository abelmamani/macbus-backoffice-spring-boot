package service.usecases;

import service.exceptions.ServiceNotExistsException;
import service.inputs.DeleteServiceInput;
import service.outputs.DeleteServiceRepository;

public class DeleteServiceUseCase implements DeleteServiceInput {
    private DeleteServiceRepository deleteServiceRepository;

    public DeleteServiceUseCase(DeleteServiceRepository deleteServiceRepository) {
        this.deleteServiceRepository = deleteServiceRepository;
    }

    @Override
    public void deleteService(Long id) {
        if(!deleteServiceRepository.existsById(id))
            throw new ServiceNotExistsException("El servicio con id " + id + " no existe.");
        deleteServiceRepository.deleteById(id);
    }
}
