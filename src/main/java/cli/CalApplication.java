package cli;

import domain.DomainController;
import domain.Event;

import java.time.LocalDate;
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
        int max = 2;
        do {
            try {
                System.out.println("1. make event.");
                System.out.println("2. show all events.");
                System.out.println("3. stop the program.");
                System.out.print("=> ");
                int choice = parseInt(input.next());
                if (choice < 1 || choice > 3)
                    throw new IllegalArgumentException(String.format("pls choose a number between %d and %d", min, max));
                if (choice == 1)
                    makeEvent();
                if (choice == 2)
                    showAllEvents();
                if (choice == 3)
                    stop = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!stop);
    }

    private void makeEvent() {
        System.out.print("\nTitle: ");
        String title = input.next();
        System.out.printf("%nToday's date = %d/%d/%4d%nDate (MM/DD/YYYY): ",
                LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), LocalDate.now().getYear());
        String date = input.next();
        System.out.print("\bStart hour\n(__h__): ");
        String startHour = input.next();
        System.out.print("\nEnd hour\n(__h__): ");
        String endHour = input.next();
        System.out.println();
        if (choice("description"))
            addDescription();
    }

    private void addDescription() {
        String description = input.next();
        dc.addDescription(description);
    }

    private boolean choice(String thingToAdd) {
        System.out.printf("Do you want to add %s%n(Y/N)=> ", thingToAdd);
        String choice = input.next();
        System.out.println();
        if (!choice.matches("^[yYnN]"))
            throw new IllegalArgumentException("not valid choice only Y for yes or N for no");
        return choice.matches("^[yY]");
    }

    private void showAllEvents() {
        for (Event event : dc.giveAllEventsSortedByDate()) {
            System.out.println(event);
        }
    }
}
