package ar.edu.undec.adapter.data.route.crud;

import ar.edu.undec.adapter.data.route.models.RouteNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import route.models.RouteGeneralInfoResponseModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteCRUD extends Neo4jRepository<RouteNode, Long> {
    boolean existsByShortName(String shortName);
    boolean existsByLongName(String longName);
    void deleteByLongName(String longName);
    Optional<RouteNode> findByLongName(String longName);
    @Query("MATCH (r:Route) RETURN r {.short_name, .long_name, .description, .color, .text_color, .route_status}")
    List<RouteGeneralInfoResponseModel> findAllRoutesGeneralInfo();
}