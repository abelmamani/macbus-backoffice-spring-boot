package stop.outputs;

public interface DeleteStopRepository {
    boolean existsById(Long id);
    void deleteById(Long id);
}
