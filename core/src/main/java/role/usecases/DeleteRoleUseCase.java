package role.usecases;

import role.exceptions.RoleException;
import role.inputs.DeleteRoleInput;
import role.outputs.RoleRepository;

public class DeleteRoleUseCase implements DeleteRoleInput {
    private RoleRepository roleRepository;

    public DeleteRoleUseCase(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void deleteRole(String id) {
        if (!roleRepository.existsById(id))
            throw new RoleException("El rol a eliminar no existe.");
        if (roleRepository.isUsedByUsers(id))
            throw new RoleException("El rol no puede eliminarse porque está en uso.");
        roleRepository.deleteById(id);
    }
}
