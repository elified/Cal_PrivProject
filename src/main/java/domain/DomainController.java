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
        return eRepo.getEventList().stream().sorted(Comparator.comparing(Event::getDate)).toList();
    }

    public void addEvent(String title, String date, String startTime, String endTime) {
        LocalDate localDate = eRepo.makeDate(date);
        LocalTime startHour = eRepo.makeHour(startTime);
        LocalTime endHour = eRepo.makeHour(endTime);
        eRepo.addEvent(new Event(title, localDate, startHour, endHour));
    }

    public void addDescription(String description) {
        eRepo.addDescription(description);
    }
}
