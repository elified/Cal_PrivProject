package startupGui;

import domain.DomainController;
import gui.SceneMaintainer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cal Project");

        // makes a cental point of maintenance over different Scenes
        SceneMaintainer sm = new SceneMaintainer(primaryStage, new DomainController());
        sm.showMainScene(); // calls the first scene called mainScene

        primaryStage.show();
    }
}