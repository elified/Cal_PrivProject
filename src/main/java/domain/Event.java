package domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private static final LocalDate MAX_DATE = LocalDate.of(LocalDate.now().getYear() + 100, 12, 31);
    private static final LocalDate MIN_DATE = LocalDate.of((LocalDate.now().getYear() / 100 - 1) * 100, 1, 1);
    private int id;
    private String title;
    private LocalDate date;
    private LocalTime startHour;
    private LocalTime endHour;
    private String description;

    public Event(String title, LocalDate date, LocalTime startHour, LocalTime endHour) {
        this(title, date, startHour, endHour, "No description");
    }

    public Event(String title, LocalDate date, LocalTime startHour, LocalTime endHour, String description) {
        this(0, title, date, startHour, endHour, description);
    }

    public Event(int id, String title, LocalDate date, LocalTime startHour, LocalTime endHour, String description) {
        setId(id);
        setTitle(title);
        setDate(date);
        setStartHour(startHour);
        setEndHour(endHour);
        setDescription(description);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Title: %s, Date: %d/%d/%4d, start hour: %d:%02d, end hour: %d:%02d, description: %s",
                this.id, this.title, this.date.getMonthValue(), this.date.getDayOfMonth(), this.date.getYear(),
                this.startHour.getHour(), this.startHour.getMinute(), this.endHour.getHour(),
                this.endHour.getMinute(), this.description);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Title can't be blank");
        this.title = title;
    }

    public void setDate(LocalDate date) {
        if (date.isBefore(MIN_DATE) || date.isAfter(MAX_DATE))
            throw new IllegalArgumentException(String.format("Choose a date between %2d/%d/%4d and %2d/%d/%4d",
                    MIN_DATE.getDayOfMonth(), MIN_DATE.getMonthValue(), MIN_DATE.getYear(),
                    MAX_DATE.getDayOfMonth(), MAX_DATE.getMonthValue(), MAX_DATE.getYear()));
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