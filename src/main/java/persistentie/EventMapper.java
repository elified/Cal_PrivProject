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
    }

    public List<Event> getDummyEvents() {
        return dummyEvents;
    }
}
