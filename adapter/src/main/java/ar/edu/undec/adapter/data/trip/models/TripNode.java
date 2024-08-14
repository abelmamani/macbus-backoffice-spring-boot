package ar.edu.undec.adapter.data.trip.models;

import ar.edu.undec.adapter.data.service.models.ServiceNode;
import ar.edu.undec.adapter.data.stoptime.models.StopTimeNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import trip.models.TripStatus;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node("Trip")
public class TripNode {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String id;
    @Property("departure_time")
    private LocalTime departureTime;
    @Property("trip_status")
    private TripStatus tripStatus;
    @Relationship(type = "TRIP_AT", direction = Relationship.Direction.OUTGOING)
    private ServiceNode service;
    @Relationship(type = "PART_OF_TRIP", direction = Relationship.Direction.INCOMING)
    private List<StopTimeNode> stopTImes;
}

