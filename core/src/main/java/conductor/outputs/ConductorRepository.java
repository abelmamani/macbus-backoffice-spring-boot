package conductor.outputs;

import conductor.models.ConductorResponseModel;
import user.models.User;
import java.util.Collection;
import java.util.Optional;

public interface ConductorRepository {
    boolean existsByEmail(String email);
    String encodePassword(String password);
    Optional<User> findByEmail(String email);
    void deleteByEmail(String email);
    void save(User user);
    Collection<ConductorResponseModel> findConductors();
}
