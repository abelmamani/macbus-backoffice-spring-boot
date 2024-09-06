package busservice.outputs;

public interface DeleteServiceRepository {
    boolean existsByName(String name);
    void deleteByName(String name);
}
