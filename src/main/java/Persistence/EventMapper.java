package Persistence;

import domain.Event;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventMapper {
    private static final String SELECT_ALL_EVENTS = "SELECT id, title, date, startHour, endHour, description FROM calendar.events";
    private static final String UPLOAD_NEW_EVENT = "INSERT INTO calendar.events (title, date, startHour, endHour, description) VALUES (?, ?, ?, ?, ?)";

    /**
     * grabs all the events out of the MySQL DB (not ordered)
     *
     * @return list of events
     */
    public List<Event> giveAllEventsInDB() {
        List<Event> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(ConnectionDB.JDBC_URL);
             PreparedStatement query = conn.prepareStatement(SELECT_ALL_EVENTS);
             ResultSet rs = query.executeQuery()) {
            while (rs.next()) {
                try {
                    result.add(mapToEvent(rs));
                } catch (IllegalArgumentException ex) {
                    // goes to the next event
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    /**
     * makes the data out of the DB into an event
     *
     * @param rs the result of the sql query
     * @return Event object
     * @throws SQLException
     */
    private Event mapToEvent(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        LocalDate date = rs.getDate("date").toLocalDate();
        LocalTime startTime = rs.getTime("startHour").toLocalTime();
        LocalTime endTime = rs.getTime("endHour").toLocalTime();
        String description = rs.getString("description");
        return new Event(id, title, date, startTime, endTime, description);
    }

    /**
     * uploads new event into the DB
     *
     * @param event that just got made from the CLI / GUI
     * @return the new AUTO-INCREMENT int from the SQL DB
     */
    public int uploadNewEvent(Event event) {
        try (Connection conn = DriverManager.getConnection(ConnectionDB.JDBC_URL);
             PreparedStatement query = conn.prepareStatement(UPLOAD_NEW_EVENT, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, (event.getTitle()));
            query.setDate(2, Date.valueOf(event.getDate()));
            query.setTime(3, Time.valueOf(event.getStartHour()));
            query.setTime(4, Time.valueOf(event.getEndHour()));
            query.setString(5, event.getDescription());
            query.executeUpdate();

            try (ResultSet keys = query.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1); // newly generated int / ID from DB
                }
            }

            throw new SQLException("No generated key returned.");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
