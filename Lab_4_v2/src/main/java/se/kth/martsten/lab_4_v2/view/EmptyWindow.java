package se.kth.martsten.lab_4_v2.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class EmptyWindow extends ProcessingWindow {

    EmptyWindow(Controller controller) {
        super(controller);
    }

    @Override
    protected void createNodes() {
        this.setPadding(new Insets(20));
        this.setSpacing(10);
        this.getChildren().addAll(
                new Label("● Import an image by clicking File → Load Image."),
                new Label("● Add effects to an image by clicking Generate → ..."),
                new Label("● Export an image by clicking File → Save Image."));
    }
}
