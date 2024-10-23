package ar.edu.undec.adapter.data.trip.models;

import ar.edu.undec.adapter.data.busservice.models.ServiceNode;
import ar.edu.undec.adapter.data.stoptime.models.StopTimeNode;
import ar.edu.undec.adapter.data.tripupdate.models.TripUpdateNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import trip.models.TripStatus;
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
    @Property(name = "departure_time")
    private String departureTime;
    @Property(name = "trip_status")
    private TripStatus tripStatus;
    @Relationship(type = "TRIP_AT", direction = Relationship.Direction.OUTGOING)
    private ServiceNode service;
    @Relationship(type = "HAS_UPDATE", direction = Relationship.Direction.OUTGOING)
    private TripUpdateNode tripUpdate;
    @Relationship(type = "PART_OF_TRIP", direction = Relationship.Direction.INCOMING)
    private List<StopTimeNode> stopTImes;
}

