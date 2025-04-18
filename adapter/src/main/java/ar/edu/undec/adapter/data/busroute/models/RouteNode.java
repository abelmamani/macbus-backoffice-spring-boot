package ar.edu.undec.adapter.data.busroute.models;

import ar.edu.undec.adapter.data.audit.AuditableNode;
import ar.edu.undec.adapter.data.shape.models.ShapeNode;
import ar.edu.undec.adapter.data.stopsequence.models.StopSequenceNode;
import ar.edu.undec.adapter.data.trip.models.TripNode;
import audit.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import busroute.models.RouteProgressStatus;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node("Route")
public class RouteNode extends AuditableNode{
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String id;
    @Property(name = "short_name")
    private String shortName;
    @Property(name = "long_name")
    private String longName;
    @Property(name = "description")
    private String description;
    @Property(name = "color")
    private String color;
    @Property(name = "text_color")
    private String textColor;
    @Property(name = "progress_status")
    private RouteProgressStatus progressStatus;
    @Property(name = "status")
    private EntityStatus status;
    @Relationship(type = "HAS_SHAPE", direction = Relationship.Direction.OUTGOING)
    private List<ShapeNode> shapes;
    @Relationship(type = "HAS_STOP", direction = Relationship.Direction.OUTGOING)
    private List<StopSequenceNode> stopSequences;
    @Relationship(type = "HAS_TRIP", direction = Relationship.Direction.OUTGOING)
    private List<TripNode> trips;
}
