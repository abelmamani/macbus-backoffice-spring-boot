package privilege.outputs;

import privilege.models.Privilege;
import java.util.Collection;
import java.util.Optional;

public interface PrivilegeRepository {
    boolean existsByName(String name);
    Optional<Privilege> findByName(String name);
    Collection<Privilege> findAll();
}
