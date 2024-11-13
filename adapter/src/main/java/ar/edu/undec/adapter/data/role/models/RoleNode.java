package ar.edu.undec.adapter.data.role.models;

import ar.edu.undec.adapter.data.privilege.models.PrivilegeNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Node("Role")
public class RoleNode {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String name;
    @Relationship(type = "HAS_PRIVILEGE")
    private List<PrivilegeNode> privileges;
}
