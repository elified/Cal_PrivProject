package startup;

import cli.CalApplication;
import domain.DomainController;

public class Startup {
    public static void main(String[] args) {
        new CalApplication(new DomainController()).start();
    }
}