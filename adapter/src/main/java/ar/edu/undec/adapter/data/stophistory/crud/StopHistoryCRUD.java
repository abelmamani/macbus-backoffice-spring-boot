package ar.edu.undec.adapter.data.stophistory.crud;

import ar.edu.undec.adapter.data.stophistory.models.StopHistoryNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import stophistory.models.StopHistoryCountsResponseModel;
import java.util.List;


@Repository
public interface StopHistoryCRUD extends Neo4jRepository<StopHistoryNode, String> {
    @Query("MATCH (s:Stop)-[:HAS_HISTORY]->(sh:StopHistory) " +
            "WHERE (sh.date >= $startDate AND sh.date <= $endDate) " +
            "RETURN s.name AS name, count(sh) AS total")
    List<StopHistoryCountsResponseModel> findStopHistoryCounts(String startDate, String endDate);
}
