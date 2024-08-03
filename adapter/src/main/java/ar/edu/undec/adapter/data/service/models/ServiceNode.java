package ar.edu.undec.adapter.data.service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node("Service")
public class ServiceNode {
    @Id @GeneratedValue
    private Long id;
    private String name;
    @Property("start_name")
    private LocalDate startDate;
    @Property("emd_name")
    private LocalDate endDate;
}
