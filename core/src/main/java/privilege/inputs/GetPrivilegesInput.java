package privilege.inputs;

import privilege.models.Privilege;
import java.util.Collection;

public interface GetPrivilegesInput {
    Collection<Privilege> getPrivileges();
}
