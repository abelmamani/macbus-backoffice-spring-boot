package ar.edu.undec.adapter.data.stop.models;

import ar.edu.undec.adapter.data.audit.AuditableNode;
import audit.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import stop.models.StopAssignedStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node("Stop")
public class StopNode extends AuditableNode {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String id;
    @Property(name = "name")
    private String name;
    @Property(name = "latitude")
    private Double latitude;
    @Property(name = "longitude")
    private Double longitude;
    @Property(name = "assigned_status")
    private StopAssignedStatus assignedStatus;
    @Property(name = "status")
    private EntityStatus status;
}
