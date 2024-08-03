package ar.edu.undec.adapter.data.stop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node("Stop")
public class StopNode {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
}
