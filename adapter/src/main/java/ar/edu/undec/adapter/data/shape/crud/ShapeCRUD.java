package ar.edu.undec.adapter.data.shape.crud;

import ar.edu.undec.adapter.data.shape.models.ShapeNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface ShapeCRUD extends Neo4jRepository<ShapeNode, String> {
    @Query("MATCH (r:Route {long_name: $longName})-[:HAS_SHAPE]->(s:Shape)" +
            "RETURN { latitude: s.latitude, longitude: s.longitude, sequence: s.sequence, " +
            "distanceTraveled: s.distance_traveled" +
            "} AS shape")
    List<Map<String, Object>> findAllShapesByRouteLongName(String longName);
    @Query("MATCH (r:Route {long_name: $longName})-[rel:HAS_SHAPE]->(s:Shape) DETACH DELETE s")
    void deleteShapesByRoute(String longName);
}