package ar.edu.undec.adapter.data.privilege.mapper;

import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import ar.edu.undec.adapter.data.privilege.models.PrivilegeNode;
import privilege.models.Privilege;

public class PrivilegeDataMapper {
    public static Privilege dataCoreMapper(PrivilegeNode privilegeNode){
        try {
            return Privilege.getInstance(privilegeNode.getId(), privilegeNode.getName());
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from node to core");
        }
    }

    public static PrivilegeNode dataNodeMapper(Privilege privilege){
        try {
            return PrivilegeNode.builder()
                    .id(privilege.getId())
                    .name(privilege.getName())
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }
}