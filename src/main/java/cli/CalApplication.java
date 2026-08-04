package cli;

import domain.DomainController;

public class CalApplication {
    private DomainController dc;

    public CalApplication(DomainController dc) {
        this.dc = dc;
    }

    public void start() {
        System.out.println("it works!");
    }
}
