package ar.edu.undec.adapter.data.shape.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node("Shape")
public class ShapeNode {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String id;
    private Double latitude;
    private Double longitude;
    private Integer sequence;
    @Property("distance_traveled")
    private Integer distanceTraveled;
}

