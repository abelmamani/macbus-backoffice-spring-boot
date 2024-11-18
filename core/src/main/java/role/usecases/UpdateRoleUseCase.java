package role.usecases;

import audit.EntityStatus;
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
        String status = updateRoleRequestModel.getStatus();
        List<String> privileges = updateRoleRequestModel.getPrivileges();

        validateStringField(roleId, "id del rol");
        validateStringField(roleName, "nombre del rol");

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

        EntityStatus newStatus = foundRole.getStatus();

        if (newStatus.equals(EntityStatus.INACTIVE)) {
            validateStringField(status, "estado del rol");
            try {
                 newStatus = EntityStatus.valueOf(status);
            } catch (IllegalArgumentException e) {
                throw new RoleException("El estado " + status + " no es válido.");
            }
        }
        if(roleRepository.existsByName(updateRoleRequestModel.getName()) && !foundRole.getName().equals(updateRoleRequestModel.getName()))
            throw new RoleException("El rol con nombre "+ updateRoleRequestModel.getName()+ " ya existe, elige otro.");

        List<Privilege> newPrivileges = privileges.stream()
                .map(privilege -> {
                    return privilegeRepository.findByName(privilege)
                            .orElseThrow(() -> new PrivilegeException("El privilegio '" + privilege + "' no existe."));
                }).collect(Collectors.toList());

        Role role = Role.getInstance(roleId, roleName, newStatus, newPrivileges);
        roleRepository.save(role);
    }
    private void validateStringField(String field, String fieldName) {
        if (field == null || field.isBlank()) {
            throw new RoleException("El " + fieldName + " es requerido.");
        }
    }
}
