package role.usecases;

import audit.EntityStatus;
import role.inputs.GetRolesInput;
import role.models.Role;
import role.outputs.RoleRepository;
import java.util.Collection;

public class GetRolesUseCase implements GetRolesInput {
    private RoleRepository roleRepository;

    public GetRolesUseCase(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Collection<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Collection<Role> getActiveRoles() {
        return roleRepository.findAllByStatus(EntityStatus.ACTIVE);
    }
}
