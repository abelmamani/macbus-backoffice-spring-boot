package role.outputs;

import role.models.Role;
import java.util.Collection;
import java.util.Optional;

public interface RoleRepository {
    boolean existsById(String id);
    boolean existsByName(String name);
    boolean isUsedByUsers(String id);
    Optional<Role> findById(String id);
    Optional<Role> findByName(String name);
    void deleteById(String id);
    void save(Role role);
    Collection<Role> findAll();
}
