package domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Event {
    private static final LocalDate MAXDATE = LocalDate.of(LocalDate.now().getYear() + 100, 12, 31);
    private static final LocalDate MINDATE = LocalDate.of(1900, 1, 1);
    private String title;
    private LocalDate date;
    private LocalTime startHour;
    private LocalTime endHour;
    private String description;

    public Event(String title, LocalDate date, LocalTime startHour, LocalTime endHour) {
        this(title, date, startHour, endHour, "No description");
    }

    public Event(String title, LocalDate date, LocalTime startHour, LocalTime endHour, String description) {
        setTitle(title);
        setDate(date);
        setStartHour(startHour);
        setEndHour(endHour);
        setDescription(description);
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Date: %d/%d/%4d, start hour: %dh%02d, end hour: %dh%02d, description: %s",
                this.title, this.date.getMonthValue(), this.date.getDayOfMonth(), this.date.getYear(),
                this.startHour.getHour(), this.startHour.getMinute(), this.endHour.getHour(),
                this.endHour.getMinute(), this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(title, event.title) && Objects.equals(date, event.date) && Objects.equals(startHour, event.startHour) && Objects.equals(endHour, event.endHour);
    }


    @Override
    public int hashCode() {
        return Objects.hash(title, date, startHour, endHour);
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Title can't be blank");
        this.title = title;
    }

    public void setDate(LocalDate date) {
        if (date.isBefore(MINDATE) || date.isAfter(MAXDATE))
            throw new IllegalArgumentException(String.format("Choose a date between %2d/%d/%4d and %2d/%d/%4d",
                    MINDATE.getDayOfMonth(), MINDATE.getMonthValue(), MINDATE.getYear(),
                    MAXDATE.getDayOfMonth(), MAXDATE.getMonthValue(), MAXDATE.getYear()));
        this.date = date;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public void setEndHour(LocalTime endHour) {
        if (endHour.isBefore(this.startHour))
            throw new IllegalArgumentException("End hour can't be before the start hour.");
        if (endHour.equals(startHour))
            throw new IllegalArgumentException("End hour can't be at the exact same time as the start hour.");
        this.endHour = endHour;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank())
            description = "No description";
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public String getDescription() {
        return description;
    }
}
