package ar.edu.undec.adapter.data.busservice.models;

import ar.edu.undec.adapter.data.audit.AuditableNode;
import ar.edu.undec.adapter.data.calendardate.models.CalendarDateNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node("Service")
public class ServiceNode extends AuditableNode {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String id;
    @Property(name = "name")
    private String name;
    @Property(name = "start_date")
    private String startDate;
    @Property(name = "end_date")
    private String endDate;
    @Relationship(type = "HAS_CALENDAR_DATE", direction = Relationship.Direction.OUTGOING)
    private List<CalendarDateNode> calendarDates;
}
