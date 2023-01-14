package se.kth.martsten.lab_4_v2;

import javafx.application.Application;
import javafx.stage.Stage;
import se.kth.martsten.lab_4_v2.model.ImageProcessor;
import se.kth.martsten.lab_4_v2.view.View;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        ImageProcessor imageProcessor = new ImageProcessor();
        new View(primaryStage, imageProcessor);
    }

    public static void main(String[] args) {
        launch();
    }
}