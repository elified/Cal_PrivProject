package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainScene {
    private final SceneMaintainer maintainer;

    public MainScene(SceneMaintainer maintainer) {
        this.maintainer = maintainer;
    }

    public Scene createScene() {
        Label test = new Label("This is the main screen");
        test.autosize();
        Button btn = new Button();
        btn.setText("Add event");
        btn.setOnAction(event -> maintainer.showAddEventScene());
        VBox button = new VBox(12, btn);

        VBox root = new VBox(12, test, button);
        return new Scene(root, 640, 360);
    }
}
