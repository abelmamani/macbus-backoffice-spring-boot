package ar.edu.undec.adapter.data.stoptime.models;

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
@Node("StopTime")
public class StopTimeNode {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String id;
    @Property(name = "arrival_time")
    private String arrivalTime;
    @Property(name = "distance_traveled")
    private Long distanceTraveled;
    @Property(name = "headsign")
    private String headsign;
    @Relationship(type = "LOCATED_AT", direction = Relationship.Direction.OUTGOING)
    private StopNode stop;
}

