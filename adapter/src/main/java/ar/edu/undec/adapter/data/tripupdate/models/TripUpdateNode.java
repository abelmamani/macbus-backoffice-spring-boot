package ar.edu.undec.adapter.data.tripupdate.models;

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
@Node("TripUpdate")
public class TripUpdateNode {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String id;
    @Property(name = "latitude")
    private Double latitude;
    @Property(name = "longitude")
    private Double longitude;
    @Property(name = "timestamp")
    private Long timestamp;
}

