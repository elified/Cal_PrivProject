-- Initialize the calendar database with events table
USE calendar;

CREATE TABLE IF NOT EXISTS events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    startHour TIME NOT NULL,
    endHour TIME NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- adding dummy events
INSERT INTO events (title, date, startHour, endHour) 
VALUES ("Work", "2026-8-20", "9:00", "17:00"),
        ("Hobby", "2026-8-29", "9:00", "17:00"),
        ("Start vacation", "2026-8-27", "9:00", "17:00"),
        ("Out with the boys!!!", "2026-8-28", "9:00", "17:00"),
        ("Cleaning", "2026-8-24", "9:00", "17:00"),
        ("Work from home", "2026-8-23", "9:00", "17:00"),
        ("Work", "2026-8-21","9:00", "17:00"),
        ("Work", "2026-8-22", "9:00","17:00");