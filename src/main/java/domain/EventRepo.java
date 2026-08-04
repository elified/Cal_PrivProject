package domain;

import persistentie.EventMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class EventRepo {
    private EventMapper eventMapper;
    private List<Event> eventList;

    public EventRepo() {
        this.eventMapper = new EventMapper();
        eventList = new ArrayList<>(eventMapper.getDummyEvents());
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void addDescription(String description) {
        eventList.getLast().setDescription(description);
    }

    public void addEvent(Event event) {
        eventList.add(event);
    }

    public LocalDate makeDate(String date) {
        if (!date.matches("^[1-9]([0-9])[-/][1-9]([0-9])[-/][1-9][0-9]([0-9]{2})"))
            throw new IllegalArgumentException("Not a valid date. (Month/day/year)");
        String[] split = date.split("[-/]");
        int month = parseInt(split[1]);
        int day = parseInt(split[2]);
        int year = parseInt(split[3]);
        return LocalDate.of(month, day, year);
    }

    public LocalTime makeHour(String hourString) {
        if (!hourString.matches("^[1-9]([0-9])[hH]([0-9])[1-9]"))
            throw new IllegalArgumentException("Not a valid hour. (__h__)");
        String[] split = hourString.split("[hH]");
        int hour = parseInt(split[1]);
        int minutes = parseInt(split[2]);
        return LocalTime.of(hour, minutes);
    }
}
