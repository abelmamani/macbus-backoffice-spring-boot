package route.outputs;

public interface DeleteRouteRepository {
    boolean existsByLongName(String longName);
    void deleteByLongName(String longName);
}
