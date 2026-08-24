package cli;

import domain.DomainController;
import domain.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CalApplication {
    private final Scanner input = new Scanner(System.in);
    private final DomainController dc;
    private boolean showSummery = true;

    public CalApplication(DomainController dc) {
        this.dc = dc;
    }

    public void start() {
        boolean stop = false;
        int min = 1;
        int max = 4;
        do {
            try {
                if (showSummery)
                    showSummeryEvents();
                else this.showSummery = true;
                System.out.printf("%d. make event for today.%n", min);
                System.out.printf("%d. make custom event.%n", min + 1);
                System.out.printf("%d. show all events.%n", max - 1);
                System.out.printf("%d. stop the program.%n", max);
                System.out.print("=> ");
                int choice = parseInt(input.nextLine());
                if (choice < min || choice > max)
                    throw new IllegalArgumentException(String.format("%npls choose a number between %d and %d.%n", min, max));
                if (choice == min)
                    makeEvent(true);
                if (choice == min + 1)
                    makeEvent(false);
                if (choice == max - 1)
                    showAllEvents();
                if (choice == max)
                    stop = true;
            } catch (NumberFormatException exception) {
                System.out.printf("%npls choose a number between %d and %d, not a letter.%n%n", min, max);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        } while (!stop);
        System.out.printf("%nBye bye!");
    }

    /**
     * goes through the variables needed to make an event, description is optional
     *
     * @param currentDay depending on if the user chose 1 or 2 in the start() it's true or false
     */
    private void makeEvent(boolean currentDay) {
        boolean valid = false;
        do {
            try {
                System.out.print("\nType \\ to return to the menu.\nTitle: ");
                String title = input.nextLine();
                if (title.startsWith("\\"))
                    return;
                dc.validateTitle(title);
                System.out.println();
                LocalDate localDate;
                if (!currentDay) {
                    System.out.printf("Today's date = %d/%d/%4d%nDate (MM/DD/YYYY): ",
                            LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), LocalDate.now().getYear());
                    localDate = dc.validateDate(input.nextLine());
                    System.out.println();
                } else localDate = LocalDate.now();
                System.out.printf("Start hour%n(__:__): ");
                LocalTime startHour = dc.validateHour(input.nextLine());
                System.out.println();
                System.out.printf("End hour%n(__:__): ");
                LocalTime endHour = dc.validateHour(input.nextLine());
                System.out.println();
                dc.addEvent(title, localDate, startHour, endHour);
                if (choice())
                    addDescription();
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    /**
     * adds the description by taking the last added event and attaching the description
     * since the last made event is always the one the user just made
     */
    private void addDescription() {
        System.out.printf("Your description: %n");
        String description = input.nextLine();
        System.out.println();
        dc.addDescription(description);
    }

    private boolean choice() {
        System.out.printf("Do you want to add description?%n(Y/N)=> ");
        String choice = input.nextLine();
        System.out.println();
        if (!choice.matches("^[yYnN]"))
            throw new IllegalArgumentException("not valid choice only Y for yes or N for no");
        return choice.matches("^[yY]");
    }

    /**
     * prints out all the events we have locally stored sorted by date and
     * prints them on the screen using the toString() methods of the class Event
     */
    private void showAllEvents() {
        this.showSummery = false;
        System.out.println();
        for (Event event : dc.giveAllEventsSortedByDate()) {
            System.out.println(event);
        }
        System.out.println();
    }

    /**
     * prints out the first five events in the locally saved event list sorted by date
     * (i know there is probably a cleaner way to do this but this worked for me and i'm not gone change it (for now))
     */
    private void showSummeryEvents() {
        System.out.println();
        int i = 0;
        for (Event event : dc.giveAllEventsSortedByDate()) {
            System.out.println(event);
            i++;
            if (i >= 5 || i == dc.giveAllEventsSortedByDate().size())
                break;
        }
        System.out.println();
    }
}