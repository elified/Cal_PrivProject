package cli;

import domain.DomainController;
import domain.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CalApplication {
    private final Scanner input = new Scanner(System.in);
    private DomainController dc;

    public CalApplication(DomainController dc) {
        this.dc = dc;
    }

    public void start() {
        boolean stop = false;
        int min = 1;
        int max = 3;
        do {
            try {
                System.out.println("1. make event.");
                System.out.println("2. show all events.");
                System.out.println("3. stop the program.");
                System.out.print("=> ");
                int choice = parseInt(input.nextLine());
                if (choice < min || choice > max)
                    throw new IllegalArgumentException(String.format("pls choose a number between %d and %d.%n%n", min, max));
                if (choice == 1)
                    makeEvent();
                if (choice == 2)
                    showAllEvents();
                if (choice == 3)
                    stop = true;
            } catch (NumberFormatException exception) {
                System.out.printf("pls choose a number between %d and %d, not a letter.%n%n", min, max);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        } while (!stop);
        System.out.printf("%nBye bye!");
    }

    private void makeEvent() {
        boolean valid = false;
        do {
            try {
                System.out.print("\nTitle: ");
                String title = dc.validateTitle(input.nextLine());
                System.out.println();
                System.out.printf("Today's date = %d/%d/%4d%nDate (MM/DD/YYYY): ",
                        LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), LocalDate.now().getYear());
                LocalDate localDate = dc.validateDate(input.nextLine());
                System.out.println();
                System.out.printf("Start hour%n(__h__): ");
                LocalTime startHour = dc.validateHour(input.nextLine());
                System.out.println();
                System.out.printf("End hour%n(__h__): ");
                LocalTime endHour = dc.validateHour(input.nextLine());
                System.out.println();
                dc.addEvent(title, localDate, startHour, endHour);
                if (choice("description"))
                    addDescription();
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);

    }

    private void addDescription() {
        System.out.printf("Your description: %n");
        String description = input.nextLine();
        System.out.println();
        dc.addDescription(description);
    }

    private boolean choice(String thingToAdd) {
        System.out.printf("Do you want to add %s%n(Y/N)=> ", thingToAdd);
        String choice = input.nextLine();
        System.out.println();
        if (!choice.matches("^[yYnN]"))
            throw new IllegalArgumentException("not valid choice only Y for yes or N for no");
        return choice.matches("^[yY]");
    }

    private void showAllEvents() {
        System.out.println();
        for (Event event : dc.giveAllEventsSortedByDate()) {
            System.out.println(event);
        }
        System.out.println();
    }
}
