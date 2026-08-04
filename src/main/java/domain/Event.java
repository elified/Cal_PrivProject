package domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
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
        return String.format("%nTitle: %s, Date: %d/%2d/%4d, start hour: %dh%02d, end hour: %dh%02d, description: %s%n",
                this.title, this.date.getMonthValue(), this.date.getDayOfMonth(), this.date.getYear(),
                this.startHour.getHour(), this.startHour.getMinute(), this.endHour.getHour(),
                this.endHour.getMinute(), this.description);
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Title Can't be left blank");
        this.title = title;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    public void setDescription(String description) {
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
