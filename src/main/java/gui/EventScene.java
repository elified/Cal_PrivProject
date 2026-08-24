package gui;

import domain.DomainController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class EventScene {
    private final SceneMaintainer maintainer;
    private final DomainController dc;

    public EventScene(SceneMaintainer maintainer) {
        this.maintainer = maintainer;
        this.dc = maintainer.getDomainController();
    }

    public Scene createScene() {
        Label title = new Label("New event");
        title.autosize();

        TextField titleTextField = new TextField("Title");
        titleTextField.setPromptText("Title");

        DatePicker datePicker = new DatePicker(LocalDate.now());

        // TODO: still have to figure out how to ask for time with a digital clock for startHour and endHour

        TextArea descriptionTextField = new TextArea();
        descriptionTextField.setPromptText("add description");

        Button submitBtn = new Button("Make event");

        submitBtn.setOnAction(event -> {
            LocalDate date = datePicker.getValue();
            // TODO: temporally prints to the screen but should make a new Event with the dc.makeEvent() method
            System.out.printf("title %s, date %d/%d/%d, description %s%n",
                    titleTextField.getText(),
                    date.getDayOfMonth(),
                    date.getMonthValue(),
                    date.getYear(),
                    descriptionTextField.getText());
        });
        VBox entries = new VBox(12, titleTextField, datePicker, descriptionTextField);

        VBox root = new VBox(12, title, entries, submitBtn);
        return new Scene(root, 640, 360);
    }
}
