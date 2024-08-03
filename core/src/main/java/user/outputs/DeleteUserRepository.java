package user.outputs;

public interface DeleteUserRepository {
    boolean existsById(Long id);
    void deleteById(Long id);
}
