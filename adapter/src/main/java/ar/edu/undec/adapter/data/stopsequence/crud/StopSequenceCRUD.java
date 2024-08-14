package ar.edu.undec.adapter.data.stopsequence.crud;

import ar.edu.undec.adapter.data.stopsequence.models.StopSequenceNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StopSequenceCRUD extends Neo4jRepository<StopSequenceNode, String> {
    @Query("""
    MATCH (s:Stop)<-[:STOP_AT]-(seq:StopSequence)<-[:HAS_STOP]-(r:Route)
    WHERE s.name = $stopName AND r.long_name <> $routeName
    RETURN COUNT(seq) > 0
    """)
    boolean isStopUsedInOtherRoutes(String stopName, String routeName);
}