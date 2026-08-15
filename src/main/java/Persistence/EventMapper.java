package Persistence;

import domain.Event;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventMapper {
    private static final String SELECT_ALLE_EVENTS = "SELECT title, date, startHour, endHour, description FROM calendar.events";

    // grabs all the events out of the MySQL DB (not ordered)
    public List<Event> giveAllEventsInDB() {
        List<Event> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(ConnectionDB.JDBC_URL);
             PreparedStatement query = conn.prepareStatement(SELECT_ALLE_EVENTS);
             ResultSet rs = query.executeQuery()) {
            while (rs.next()) {
                try {
                    result.add(mapToEvent(rs));
                } catch (IllegalArgumentException ex) {
                    // go next event
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    // makes the data out of the DB into an event
    private Event mapToEvent(ResultSet rs) throws SQLException {
        String title = rs.getString("title");
        LocalDate date = rs.getDate("date").toLocalDate();
        LocalTime startTime = rs.getTime("startHour").toLocalTime();
        LocalTime endTime = rs.getTime("endHour").toLocalTime();
        String description = rs.getString("description");

        return new Event(title, date, startTime, endTime, description);
    }
}
