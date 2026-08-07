package startupCli;

import cli.CalApplication;
import domain.DomainController;

public class StartupCli {
    public static void main(String[] args) {
        new CalApplication(new DomainController()).start();
    }
}