package ar.edu.undec.adapter.data.stoptime.models;

import ar.edu.undec.adapter.data.stop.models.StopNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node("StopTime")
public class StopTimeNode {
    @Id @GeneratedValue
    private Long id;
    @Property("arrival_time")
    private LocalTime arrivalTime;
    @Property("distance_traveled")
    private Integer distanceTraveled;
    @Relationship(type = "LOCATED_AT", direction = Relationship.Direction.OUTGOING)
    private StopNode stop;
}

