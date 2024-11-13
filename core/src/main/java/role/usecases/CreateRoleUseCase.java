package role.usecases;

import privilege.exceptions.PrivilegeException;
import privilege.models.EPrivilege;
import privilege.outputs.PrivilegeRepository;
import role.inputs.CreateRoleInput;
import role.models.CreateRoleRequestModel;
import privilege.models.Privilege;
import role.models.Role;
import role.exceptions.RoleException;
import role.outputs.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreateRoleUseCase implements CreateRoleInput {

    private RoleRepository roleRepository;
    private PrivilegeRepository privilegeRepository;

    public CreateRoleUseCase(RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public void createRole(CreateRoleRequestModel createRoleRequestModel) {
        String roleName = createRoleRequestModel.getName();
        List<String> privileges = createRoleRequestModel.getPrivileges();

        if (roleName == null || roleName.isBlank())
            throw new RoleException("El nombre del rol es requerido.");
        if (privileges == null || privileges.isEmpty())
            throw new RoleException("El rol debe tener al menos un privilegio.");

        privileges.stream().forEach(privilege -> {
            try {
                EPrivilege privilegeEnum = EPrivilege.valueOf(privilege);
            } catch (IllegalArgumentException e) {
                throw new PrivilegeException("El privilegio '" + privilege + "' no es válido.");
            }});

        if (roleRepository.existsByName(roleName))
            throw new RoleException("El rol con nombre " + roleName + " ya existe.");

        List<Privilege> newPrivileges = privileges.stream()
                .map(privilege -> {
                    return privilegeRepository.findByName(privilege)
                            .orElseThrow(() -> new PrivilegeException("El privilegio '" + privilege + "' no existe."));
                }).collect(Collectors.toList());
        Role role = Role.getInstance(null, roleName, newPrivileges);
        roleRepository.save(role);
    }
}
