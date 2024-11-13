package ar.edu.undec.adapter.data.role.mapper;

import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import ar.edu.undec.adapter.data.privilege.mapper.PrivilegeDataMapper;
import ar.edu.undec.adapter.data.role.models.RoleNode;
import role.models.Role;

import java.util.stream.Collectors;

public class RoleDataMapper {
    public static Role dataCoreMapper(RoleNode roleNode){
        try {
            return Role.getInstance(roleNode.getId(),
                    roleNode.getName(),
                    roleNode.getPrivileges().stream().map(PrivilegeDataMapper::dataCoreMapper).collect(Collectors.toList()));
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from node to core");
        }
    }

    public static RoleNode dataNodeMapper(Role role){
        try {
            return RoleNode.builder()
                    .id(role.getId())
                    .name(role.getName())
                    .privileges(role.getPrivileges().stream().map(PrivilegeDataMapper::dataNodeMapper).collect(Collectors.toList()))
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }
}