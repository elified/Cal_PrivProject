package domain;

import Persistence.EventMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class EventRepo {
    private final EventMapper eventMapper;
    private List<Event> eventList;
    private List<Event> newEventsList;

    public EventRepo() {
        this.eventMapper = new EventMapper();
        eventList = new ArrayList<>(eventMapper.giveAllEventsInDB());
        newEventsList = new ArrayList<>();
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void addDescription(String description) {
        eventList.getLast().setDescription(description);
    }

    public void addEvent(Event event) {
        checkForDuplicates(event);
        eventList.add(event);
        newEventsList.add(event);
    }

    public void uploadAllNewEvents() {
        for (Event event : newEventsList) {
            eventMapper.uploadNewEvent(event);
        }
    }

    private void checkForDuplicates(Event newEvent) {
        for (Event event : eventList) {
            if (event.equals(newEvent))
                throw new IllegalArgumentException("This event already exists");
        }
    }

    public LocalDate validateDate(String date) {
        if (!date.matches("^[1-9]([0-9])?[-/][1-9]([0-9])?[-/]'?[1-9][0-9]([0-9]{2})?"))
//        if (!date.matches("^\\d+[-/]\\d+[-/]\\d+"))
            throw new IllegalArgumentException("Not a valid date. (Month/day/year)");
        String[] split = date.split("[-/]");
        int month = parseInt(split[0]);
        int day = parseInt(split[1]);
        if (split[2].startsWith("'")) {
            split[2] = String.valueOf(LocalDate.now().getYear()).substring(0, 2) + split[2].substring(1);
        }
        int year = parseInt(split[2]);
        return LocalDate.of(year, month, day);
    }

    public LocalTime validateHour(String hourString) {
        if (!hourString.matches("^[1-9]([0-9])?:[0-9][0-9]?"))
//        if (!hourString.matches("^\\d(\\d)?[hH]\\d(\\d)?"))
            throw new IllegalArgumentException("Not a valid hour. (__:__)");
        String[] split = hourString.split(":");
        int hour = parseInt(split[0]);
        int minutes = parseInt(split[1]);
        return LocalTime.of(hour, minutes);
    }
}
