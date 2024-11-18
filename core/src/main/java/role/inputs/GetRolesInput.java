package role.inputs;

import role.models.Role;
import java.util.Collection;

public interface GetRolesInput {
    Collection<Role> getRoles();
    Collection<Role> getActiveRoles();
}
