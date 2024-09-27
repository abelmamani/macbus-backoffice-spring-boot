package ar.edu.undec.adapter.data.stophistory.models;

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
@Node("StopHistory")
public class StopHistoryNode {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String id;
    @Property(name = "date")
    private String date;
    @Property(name = "time")
    private String time;
    @Relationship(type = "HAS_HISTORY", direction = Relationship.Direction.INCOMING)
    private StopNode stop;
}
