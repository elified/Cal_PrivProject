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

    public EventRepo() {
        this.eventMapper = new EventMapper();
        eventList = new ArrayList<>(eventMapper.giveAllEventsInDB());
    }

    public List<Event> getEventList() {
        return eventList;
    }

    /**
     * adds a description to the last event in the list since it's a newly created one and
     * ask to add a description immediately after it's creation
     *
     * @param description
     */
    public void addDescription(String description) {
        eventList.getLast().setDescription(description);
    }

    /**
     * adds the event ot the DB and adds the ID from the DB to the new Event
     *
     * @param event from the CLI / GUI
     */
    public void addEvent(Event event) {
        int databaseId = eventMapper.uploadNewEvent(event); // returns an int ID that the DB assign to the event
        event.setId(databaseId); // same ID as in the DB
        eventList.add(event);
    }

    /**
     * using regex I check if the date format the user gives in is valid
     * (am also thinking of changing it to DD/MM/YYYY instead of MM/DD/YYYY
     *
     * @param date the string of MM/DD/YYYY the user gave in the CLI
     * @return a valid LocalDate value
     */
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

    /**
     * using regex I check if it's a valid hour
     *
     * @param hourString the string of HH:MM the user gave in the CLI
     * @return a valid LocalTime value
     */
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
