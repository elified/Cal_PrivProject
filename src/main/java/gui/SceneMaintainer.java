package gui;

import domain.DomainController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneMaintainer {
    private final Stage stage;
    private final DomainController dc;

    public SceneMaintainer(Stage stage, DomainController dc) {
        this.stage = stage;
        this.dc = dc;
    }

    public void showMainScene() {
        setScene(new MainScene(this).createScene());
    }

    public void showAddEventScene() {
        setScene(new EventScene(this).createScene());
    }

    /**
     * Takes the scene you pass through and makes it the front scene, sizes scene to the same scene as the previous one
     * and centralizes it
     *
     * @param scene the scene you want to be displayed
     */
    private void setScene(Scene scene) {
        stage.setScene(scene);
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    public DomainController getDomainController() {
        return dc;
    }
}
