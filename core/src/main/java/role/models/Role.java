package role.models;

import audit.EntityStatus;
import privilege.models.Privilege;
import role.exceptions.RoleException;
import java.util.List;

public class Role {
    private String id;
    private String name;
    private EntityStatus status;
    private List<Privilege> privileges;

    private Role(String id, String name, EntityStatus status, List<Privilege> privileges) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.privileges = privileges;
    }

    public static Role getInstance(String id, String name, EntityStatus status, List<Privilege> privileges) {
        if (name == null || name.isBlank())
            throw new RoleException("El nombre del rol es requerido.");

        if (status == null)
            throw new RoleException("El estado del rol es requerido.");

        if (privileges == null || privileges.isEmpty())
            throw new RoleException("El listado de privilegios es requerido y debe tener al menos un elemento.");

        return new Role(id, name.trim(), status, privileges);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EntityStatus getStatus() { return status; }

    public List<Privilege> getPrivileges() {
        return privileges;
    }
}