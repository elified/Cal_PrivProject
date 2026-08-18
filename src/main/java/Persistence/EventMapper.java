package Persistence;

import domain.Event;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventMapper {
    private static final String SELECT_ALL_EVENTS = "SELECT title, date, startHour, endHour, description FROM calendar.events";
    private static final String UPLOAD_NEW_EVENT = "INSERT INTO calendar.events (title, date, startHour, endHour, description) VALUES (?, ?, ?, ?, ?)";

    // grabs all the events out of the MySQL DB (not ordered)
    public List<Event> giveAllEventsInDB() {
        List<Event> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(ConnectionDB.JDBC_URL);
             PreparedStatement query = conn.prepareStatement(SELECT_ALL_EVENTS);
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

    // uploads new events into the DB
    public void uploadNewEvent(Event event) {
        try (Connection conn = DriverManager.getConnection(ConnectionDB.JDBC_URL);
             PreparedStatement query = conn.prepareStatement(UPLOAD_NEW_EVENT)) {
            query.setString(1, (event.getTitle()));
            int year = event.getDate().getYear();
            int month = event.getDate().getMonthValue();
            int day = event.getDate().getDayOfMonth();
            query.setDate(2, new Date(year, month, day));
            int startHour = event.getStartHour().getHour();
            int startMinute = event.getStartHour().getMinute();
            int startSecond = event.getStartHour().getSecond();
            query.setTime(3, new Time(startHour, startMinute, startSecond));
            int endHour = event.getEndHour().getHour();
            int endMinute = event.getEndHour().getMinute();
            int endSecond = event.getEndHour().getSecond();
            query.setTime(4, new Time(endHour, endMinute, endSecond));
            query.setString(5, event.getDescription());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
