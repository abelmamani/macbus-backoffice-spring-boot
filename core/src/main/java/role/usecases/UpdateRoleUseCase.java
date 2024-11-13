package role.usecases;

import privilege.exceptions.PrivilegeException;
import privilege.models.EPrivilege;
import privilege.models.Privilege;
import privilege.outputs.PrivilegeRepository;
import role.exceptions.RoleException;
import role.inputs.UpdateRoleInput;
import role.models.Role;
import role.models.UpdateRoleRequestModel;
import role.outputs.RoleRepository;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateRoleUseCase implements UpdateRoleInput {
    private RoleRepository roleRepository;
    private PrivilegeRepository privilegeRepository;

    public UpdateRoleUseCase(RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public void updateRole(UpdateRoleRequestModel updateRoleRequestModel) {
        String roleId = updateRoleRequestModel.getId();
        String roleName = updateRoleRequestModel.getName();
        List<String> privileges = updateRoleRequestModel.getPrivileges();

        if (roleId == null || roleId.isBlank())
            throw new RoleException("El id del rol es requerido.");
        if (roleName == null || roleName.isBlank())
            throw new RoleException("El nombre del rol es requerido.");
        if (privileges == null || privileges.isEmpty())
            throw new RoleException("El rol debe tener al menos un privilegio.");

        privileges.stream().forEach(privilege -> {
            try {
                EPrivilege privilegeEnum = EPrivilege.valueOf(privilege);
            } catch (IllegalArgumentException e) {
                throw new PrivilegeException("El privilegio '" + privilege + "' no es válido.");
            }
        });

        Role foundRole = roleRepository.findById(updateRoleRequestModel.getId()).orElseThrow(() -> new RoleException("El rol a actualizar no existe."));
        if(roleRepository.existsByName(updateRoleRequestModel.getName()) && !foundRole.getName().equals(updateRoleRequestModel.getName()))
            throw new RoleException("El rol con nombre "+ updateRoleRequestModel.getName()+ " ya existe, elige otro.");

        List<Privilege> newPrivileges = privileges.stream()
                .map(privilege -> {
                    return privilegeRepository.findByName(privilege)
                            .orElseThrow(() -> new PrivilegeException("El privilegio '" + privilege + "' no existe."));
                }).collect(Collectors.toList());

        Role role = Role.getInstance(roleId, roleName, newPrivileges);
        roleRepository.save(role);
    }
}
