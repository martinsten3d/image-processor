package se.kth.martsten.lab_4_v2.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class ContrastWindow extends ProcessingWindow {

    ContrastWindow(Controller controller){
        super(controller);
    }

    @Override
    protected void createNodes() {
        this.setPadding(new Insets(20));
        this.setSpacing(10);

        Slider window = new Slider(0, 255, 255);
        window.setShowTickLabels(true);
        window.setShowTickMarks(true);
        window.setMajorTickUnit(64);
        window.setMinorTickCount(256);
        window.setSnapToTicks(true);

        Slider level = new Slider(0, 255, 0);
        level.setShowTickLabels(true);
        level.setShowTickMarks(true);
        level.setMajorTickUnit(64);
        level.setMinorTickCount(256);
        level.setSnapToTicks(true);

        window.valueProperty().addListener(e -> controller.handleContrastProcessing(window.getValue() / 255, level.getValue() / 255));
        level.valueProperty().addListener(e -> controller.handleContrastProcessing(window.getValue() / 255, level.getValue() / 255));

        Button reset = new Button("Reset");
        reset.setOnAction(e -> {
            window.setValue(255);
            level.setValue(0);
        });

        this.getChildren().addAll(new Label("Window:"), window, new Label("Level:"), level, reset);
    }
}