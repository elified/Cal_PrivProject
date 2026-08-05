package domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

public class DomainController {
    private EventRepo eRepo;

    public DomainController() {
        eRepo = new EventRepo();
    }

    public List<Event> giveAllEventsSortedByDate() {
        return eRepo.getEventList().stream().sorted(Comparator.comparing(Event::getDate)
                .thenComparing(Event::getStartHour).thenComparing(Event::getEndHour)).toList();
    }

    public String validateTitle(String title) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Title can't be left blank.");
        return title;
    }

    public LocalDate validateDate(String date) {
        return eRepo.validateDate(date);
    }

    public LocalTime validateHour(String hour) {
        return eRepo.validateHour(hour);
    }

    public void addEvent(String title, LocalDate date, LocalTime startHour, LocalTime endHour) {
        eRepo.addEvent(new Event(title, date, startHour, endHour));
    }

    public void addDescription(String description) {
        eRepo.addDescription(description);
    }
}
