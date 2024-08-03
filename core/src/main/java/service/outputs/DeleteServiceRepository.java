package service.outputs;

public interface DeleteServiceRepository {
    boolean existsById(Long id);
    void deleteById(Long id);
}
