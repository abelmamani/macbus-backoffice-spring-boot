package ar.edu.undec.adapter.data.privilege.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Node("Privilege")
public class PrivilegeNode {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String name;
}