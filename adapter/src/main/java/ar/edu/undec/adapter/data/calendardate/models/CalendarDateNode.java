package ar.edu.undec.adapter.data.calendardate.models;

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
@Node("CalendarDate")
public class CalendarDateNode {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String id;
    @Property(name = "date")
    private String date;
}
