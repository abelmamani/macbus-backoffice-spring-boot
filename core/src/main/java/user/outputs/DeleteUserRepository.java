package user.outputs;

public interface DeleteUserRepository {
    boolean existsByEmail(String email);
    void deleteByEmail(String email);
}
