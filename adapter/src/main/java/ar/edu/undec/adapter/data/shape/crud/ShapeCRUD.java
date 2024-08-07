package ar.edu.undec.adapter.data.shape.crud;

import ar.edu.undec.adapter.data.shape.models.ShapeNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shape.models.Shape;

import java.util.List;

@Repository
public interface ShapeCRUD extends Neo4jRepository<ShapeNode, Long> {
    @Query("MATCH (r:Route)-[:HAS_SHAPE]->(s:Shape) WHERE r.long_name = $longName RETURN s ORDER BY s.sequence")
    List<Shape> findAllShapesByRouteLongName(@Param("longName") String longName);
}
