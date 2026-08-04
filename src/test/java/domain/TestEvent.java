package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEvent {
    private final static LocalDate VALIDDATE = LocalDate.now();
    private final static String VALIDTITLE = "Test title";
    private final static LocalTime VALIDSTARTHOUR = LocalTime.of(11, 0);
    private final static LocalTime VALIDENDHOUR = LocalTime.of(11, 30);

    @ParameterizedTest
    @ValueSource(strings = {"", "      "})
    @DisplayName("Empty titles : throws exception")
    void constructor_invalidTitle_throwsException(String title) {
        assertThrows(IllegalArgumentException.class, () -> new Event(title, VALIDDATE, VALIDSTARTHOUR, VALIDENDHOUR));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test", "1", "a", "This is a valid title"})
    @DisplayName("Valid titles : makes event")
    void constructor_validTitle_makesEvent(String title) {
        Event event = new Event(title, VALIDDATE, VALIDSTARTHOUR, VALIDENDHOUR);
        assertEquals(title, event.getTitle());
    }

    @ParameterizedTest
    @ValueSource(ints = {1899, 1898, 1850, 2127, 2200})
    @DisplayName("invalid dates : throws exception")
    void constructor_invalidDate_ThrowsException(int year) {
        assertThrows(IllegalArgumentException.class, () -> new Event(VALIDTITLE, LocalDate.of(year, 1, 1), VALIDSTARTHOUR, VALIDENDHOUR));
    }

    @ParameterizedTest
    @ValueSource(ints = {1900, 1950, 2100, 2126})
    @DisplayName("valid dates : makes event")
    void constructor_validDate_makesEvent(int year) {
        LocalDate testDate = LocalDate.of(year, 1, 1);
        Event event = new Event(VALIDTITLE, testDate, VALIDSTARTHOUR, VALIDENDHOUR);
        assertEquals(testDate, event.getDate());
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 25, 27})
    @DisplayName("invalid start hours : throws exception")
    void constructor_invalidStartHour_ThrowsException(int hour) {
        LocalTime testStartHour = LocalTime.of(hour, 0);
        assertThrows(IllegalArgumentException.class, () -> new Event(VALIDTITLE, VALIDDATE, testStartHour, VALIDENDHOUR));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 10, 24})
    @DisplayName("valid start hours : makes event")
    void constructor_validStartHour_makesEvent(int hour) {
        LocalTime testStartHour = LocalTime.of(hour, 59);
        Event event = new Event(VALIDTITLE, VALIDDATE, testStartHour, VALIDENDHOUR);
        assertEquals(testStartHour, event.getStartHour());
    }

    // TODO
    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 25, 27})
    @DisplayName("invalid start hours : throws exception")
    void constructor_invalidEndHour_ThrowsException(int hour) {
        LocalTime testStartHour = LocalTime.of(hour, 0);
        assertThrows(IllegalArgumentException.class, () -> new Event(VALIDTITLE, VALIDDATE, testStartHour, VALIDENDHOUR));
    }

    // TODO
    @ParameterizedTest
    @ValueSource(ints = {0, 10, 24})
    @DisplayName("valid start hours : makes event")
    void constructor_validEndHour_makesEvent(int hour) {
        LocalTime testStartHour = LocalTime.of(hour, 59);
        Event event = new Event(VALIDTITLE, VALIDDATE, testStartHour, VALIDENDHOUR);
        assertEquals(testStartHour, event.getStartHour());
    }
}

