package se.kth.martsten.lab_4_v2.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class SaturationWindow extends ProcessingWindow {

    public SaturationWindow(Controller controller) {
        super(controller);
    }

    @Override
    protected void createNodes() {
        this.setPadding(new Insets(20));
        this.setSpacing(10);

        Slider hue = new Slider(-180, 180, 0);
        hue.setShowTickLabels(true);
        hue.setShowTickMarks(true);
        hue.setMajorTickUnit(60);
        hue.setMinorTickCount(360);
        hue.setSnapToTicks(true);

        Slider saturation = new Slider(-100, 100, 0);
        saturation.setShowTickLabels(true);
        saturation.setShowTickMarks(true);
        saturation.setMajorTickUnit(20);
        saturation.setMinorTickCount(200);
        saturation.setSnapToTicks(true);

        Slider brightness = new Slider(-100, 100, 0);
        brightness.setShowTickLabels(true);
        brightness.setShowTickMarks(true);
        brightness.setMajorTickUnit(20);
        brightness.setMinorTickCount(200);
        brightness.setSnapToTicks(true);

        hue.valueProperty().addListener(e -> controller.handleSaturationProcessing(
                hue.getValue() / 360, saturation.getValue() / 200, brightness.getValue() / 200));
        saturation.valueProperty().addListener(e -> controller.handleSaturationProcessing(
                hue.getValue() / 360, saturation.getValue() / 200, brightness.getValue() / 200));
        brightness.valueProperty().addListener(e -> controller.handleSaturationProcessing(
                hue.getValue() / 360, saturation.getValue() / 200, brightness.getValue() / 200));

        Button reset = new Button("Reset");
        reset.setOnAction(e -> {
            hue.setValue(0.5);
            saturation.setValue(0.5);
            brightness.setValue(0.5);
        });

        this.getChildren().addAll(
                new Label("Hue:"), hue,
                new Label("Saturation:"), saturation,
                new Label("Brightness:"), brightness, reset);
    }
}