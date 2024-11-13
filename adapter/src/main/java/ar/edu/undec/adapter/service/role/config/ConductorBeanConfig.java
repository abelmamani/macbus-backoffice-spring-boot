package ar.edu.undec.adapter.service.role.config;

import privilege.inputs.GetPrivilegesInput;
import privilege.outputs.PrivilegeRepository;
import privilege.usecases.GetPrivilegesUseCase;
import role.inputs.CreateRoleInput;
import role.inputs.DeleteRoleInput;
import role.inputs.GetRolesInput;
import role.inputs.UpdateRoleInput;
import role.outputs.RoleRepository;
import role.usecases.CreateRoleUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import role.usecases.DeleteRoleUseCase;
import role.usecases.GetRolesUseCase;
import role.usecases.UpdateRoleUseCase;

@Configuration
public class ConductorBeanConfig {
    @Bean
    public GetRolesInput getRolesInput(RoleRepository roleRepository){
        return new GetRolesUseCase(roleRepository);
    }
    @Bean
    public GetPrivilegesInput getPrivilegesInput(PrivilegeRepository privilegeRepository){
        return new GetPrivilegesUseCase(privilegeRepository);
    }
    @Bean
    public CreateRoleInput createRoleInput(RoleRepository roleRepository, PrivilegeRepository privilegeRepository){
        return new CreateRoleUseCase(roleRepository, privilegeRepository);
    }
    @Bean
    public UpdateRoleInput updateRoleInput(RoleRepository roleRepository, PrivilegeRepository privilegeRepository){
        return new UpdateRoleUseCase(roleRepository, privilegeRepository);
    }
    @Bean
    public DeleteRoleInput deleteRoleInput(RoleRepository roleRepository){
        return new DeleteRoleUseCase(roleRepository);
    }
}
