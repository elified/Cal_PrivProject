package persistentie;

import domain.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventMapper {
    private List<Event> dummyEvents;

    public EventMapper() {
        dummyEvents = new ArrayList<>();
        makeDummyEvent();
    }

    private void makeDummyEvent() {
        dummyEvents.add(new Event("Work", LocalDate.of(2026, 8, 20), LocalTime.of(9, 0), LocalTime.of(17, 0)));
        dummyEvents.add(new Event("Hobby", LocalDate.of(2026, 8, 29), LocalTime.of(9, 0), LocalTime.of(17, 0)));
        dummyEvents.add(new Event("Start vacation", LocalDate.of(2026, 8, 27), LocalTime.of(9, 0), LocalTime.of(17, 0)));
        dummyEvents.add(new Event("Out with the boys!!!", LocalDate.of(2026, 8, 28), LocalTime.of(9, 0), LocalTime.of(17, 0)));
        dummyEvents.add(new Event("Cleaning", LocalDate.of(2026, 8, 24), LocalTime.of(9, 0), LocalTime.of(17, 0)));
        dummyEvents.add(new Event("Work from home", LocalDate.of(2026, 8, 23), LocalTime.of(9, 0), LocalTime.of(17, 0)));
        dummyEvents.add(new Event("Work", LocalDate.of(2026, 8, 21), LocalTime.of(9, 0), LocalTime.of(17, 0)));
        dummyEvents.add(new Event("Work", LocalDate.of(2026, 8, 22), LocalTime.of(9, 0), LocalTime.of(17, 0)));
    }

    public List<Event> getDummyEvents() {
        return dummyEvents;
    }
}
