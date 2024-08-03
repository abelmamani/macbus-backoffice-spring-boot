package ar.edu.undec.adapter.data.route.models;

import ar.edu.undec.adapter.data.shape.models.ShapeNode;
import ar.edu.undec.adapter.data.stopsequence.models.StopSequenceNode;
import ar.edu.undec.adapter.data.trip.models.TripNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;
import route.models.RouteStatus;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node("Route")
public class RouteNode {
    @Id @GeneratedValue
    private Long id;
    @Property("short_name")
    private String shortName;
    @Property("long_name")
    private String longName;
    private String description;
    private String color;
    @Property("text_color")
    private String textColor;
    @Property("route_status")
    private RouteStatus routeStatus;
    @Relationship(type = "HAS_SHAPE", direction = Relationship.Direction.OUTGOING)
    private List<ShapeNode> shapes;
    @Relationship(type = "HAS_STOP", direction = Relationship.Direction.OUTGOING)
    private List<StopSequenceNode> stopSequences;
    @Relationship(type = "HAS_TRIP", direction = Relationship.Direction.OUTGOING)
    private List<TripNode> trips;
}
