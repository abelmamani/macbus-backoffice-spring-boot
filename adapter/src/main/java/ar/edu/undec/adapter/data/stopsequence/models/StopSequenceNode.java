package ar.edu.undec.adapter.data.stopsequence.models;

import ar.edu.undec.adapter.data.stop.models.StopNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node("StopSequence")
public class StopSequenceNode {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String id;
    @Property("arrival_time")
    private String arrivalTime;
    @Property("distance_traveled")
    private Long distanceTraveled;
    private String headsign;
    @Relationship(type = "STOP_AT", direction = Relationship.Direction.OUTGOING)
    private StopNode stop;
}

