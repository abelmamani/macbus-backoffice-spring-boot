package ar.edu.undec.adapter.data.stop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import stop.models.StopStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node("Stop")
public class StopNode {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String id;
    private String name;
    private Double latitude;
    private Double longitude;
    private StopStatus status;
}
