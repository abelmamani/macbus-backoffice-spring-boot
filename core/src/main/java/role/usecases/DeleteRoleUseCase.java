package role.usecases;

import audit.EntityStatus;
import role.exceptions.RoleException;
import role.inputs.DeleteRoleInput;
import role.models.Role;
import role.outputs.RoleRepository;

public class DeleteRoleUseCase implements DeleteRoleInput {
    private RoleRepository roleRepository;

    public DeleteRoleUseCase(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void deleteRole(String id) {
        Role foundRole = roleRepository.findById(id).orElseThrow(() -> new RoleException("El rol a eliminar no existe."));
        if (roleRepository.isUsedByUsers(id))
            throw new RoleException("El rol no puede eliminarse porque está en uso.");
        Role role = Role.getInstance(foundRole.getId(),
                foundRole.getName(),
                EntityStatus.INACTIVE,
                foundRole.getPrivileges());
        roleRepository.save(role);
    }
}
