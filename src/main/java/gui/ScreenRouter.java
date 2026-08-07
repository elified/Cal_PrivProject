package gui;

import domain.DomainController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenRouter {
    private final Stage stage;
    private final DomainController dc;

    public ScreenRouter(Stage stage, DomainController dc) {
        this.stage = stage;
        this.dc = dc;
    }

    public void showCal() {
        setScene(new CalMainScreen(this).createScene());
    }

    private void setScene(Scene scene) {
        stage.setScene(scene);
        stage.sizeToScene();
        stage.centerOnScreen();
    }
}
