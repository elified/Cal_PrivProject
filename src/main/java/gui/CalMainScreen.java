package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CalMainScreen {
    private final ScreenRouter router;

    public CalMainScreen(ScreenRouter router) {
        this.router = router;
    }

    public Scene createScene() {
        Label title = new Label("Test main screen");

        Button exitButton = new Button("exit");
        exitButton.setMaxWidth(Double.MAX_VALUE);
        exitButton.setOnAction(event -> {
            if (exitButton.getScene() != null && exitButton.getScene().getWindow() != null) {
                exitButton.getScene().getWindow().hide();
            }
        });

        VBox buttons = new VBox(12, exitButton);
        buttons.getStyleClass().add("flex-box");
        buttons.prefWidthProperty().bind(title.widthProperty().multiply(1.70));
        buttons.maxWidthProperty().bind(title.widthProperty().multiply(1.70));

        VBox root = new VBox(12, title, buttons);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("screen-root");
        root.setStyle("-fx-alignment: center;");

        return new Scene(root, 1280, 720);
    }
}
