package se.kth.martsten.lab_4_v2.view;

import javafx.scene.layout.VBox;

abstract public class ProcessingWindow extends VBox {

    protected final Controller controller;

    protected ProcessingWindow(Controller controller) {
        this.controller = controller;
        createNodes();
    }

    abstract protected void createNodes();
}
