package startupGui;

import domain.DomainController;
import gui.ScreenRouter;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartupGui extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Test");

        ScreenRouter router = new ScreenRouter(stage, new DomainController());
        router.showCal();

        stage.show();
    }
}
