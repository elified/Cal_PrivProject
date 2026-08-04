package startup;

import cli.CalApplication;
import domain.DomainController;

public class Startup {
    static void main() {
        new CalApplication(new DomainController()).start();
    }
}
