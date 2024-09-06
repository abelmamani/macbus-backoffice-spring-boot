package ar.edu.undec.adapter.data.calendardate.crud;

import ar.edu.undec.adapter.data.calendardate.models.CalendarDateNode;
import calendardate.models.CalendarDate;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CalendarDateCRUD extends Neo4jRepository<CalendarDateNode, String> {
    boolean existsByDate(String date);
    @Query("MATCH (cd:CalendarDate {date: $date}) DETACH DELETE cd")
    void deleteByDate(String date);
    @Query("MATCH (s:Service)-[:HAS_CALENDAR_DATE]->(cd:CalendarDate) RETURN {date: cd.date, service: s.name} ORDER BY cd.date")
    List<Map<String, Object>> findAllCalendarDates();
}
