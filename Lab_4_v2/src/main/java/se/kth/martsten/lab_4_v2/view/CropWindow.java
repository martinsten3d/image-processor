package se.kth.martsten.lab_4_v2.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class CropWindow extends ProcessingWindow {

    CropWindow(Controller controller) {
        super(controller);
    }

    @Override
    protected void createNodes() {
        this.setPadding(new Insets(20));
        this.setSpacing(10);

        Slider[] sliders = new Slider[4];
        for(int i = 0; i < 4; i++) {
            Slider slider = new Slider(0, 100, 0);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            slider.setMajorTickUnit(25);
            slider.setMinorTickCount(100);
            slider.setSnapToTicks(true);
            sliders[i] = slider;
        }
        for (Slider s : sliders)
            s.valueProperty().addListener(e -> controller.handleCropProcessing(
                    sliders[0].getValue() / 100, sliders[1].getValue() / 100, sliders[2].getValue() / 100, sliders[3].getValue() / 100));

        Button reset = new Button("Reset");
        reset.setOnAction(e -> {
            for(Slider s : sliders)
                s.setValue(0);
        });

        this.getChildren().addAll(
                new Label("Top %:"), sliders[0],
                new Label("Bottom %:"), sliders[1],
                new Label("Left %:"), sliders[2],
                new Label("Right %:"), sliders[3], reset);
    }
}