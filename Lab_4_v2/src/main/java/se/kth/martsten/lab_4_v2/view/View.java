package se.kth.martsten.lab_4_v2.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import se.kth.martsten.lab_4_v2.model.ImageProcessor;

public class View {

    private final Controller controller;
    private final VBox rightView;
    private final SplitPane splitPane;
    private final ImageView imageView;
    private final Label applicationStatus;
    final EmptyWindow emptyWindow;
    HistogramWindow histogramWindow;
    private ContrastWindow contrastWindow;
    private SaturationWindow saturationWindow;
    private CropWindow cropWindow;

    public View(Stage stage, ImageProcessor imageProcessor) {
        controller = new Controller(this, imageProcessor);
        imageView = new ImageView();
        applicationStatus = new Label();
        rightView = new VBox();
        splitPane = new SplitPane();
        emptyWindow = new EmptyWindow(controller);
        histogramWindow = new HistogramWindow(controller);
        contrastWindow = new ContrastWindow(controller);
        saturationWindow = new SaturationWindow(controller);
        cropWindow = new CropWindow(controller);
        createUIComponents(stage);
    }

    private void createUIComponents(Stage stage) {
        VBox root = new VBox();
        root.setPrefSize(1280, 720);
        VBox leftView = new VBox();

        splitPane.setPadding(new Insets(5));
        splitPane.prefHeightProperty().bind(root.heightProperty());
        splitPane.getItems().addAll(leftView, rightView);
        splitPane.setDividerPosition(0, 0.4f);

        FlowPane bottom = new FlowPane(applicationStatus);
        bottom.setPrefHeight(100);
        bottom.setPadding(new Insets(10));
        bottom.setAlignment(Pos.CENTER_LEFT);

        root.getChildren().addAll(createMenuBar(), splitPane, bottom);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(true);
        stage.setTitle("Photoshop 2");
        stage.show();

        openProcessingWindow(emptyWindow);
    }

    private MenuBar createMenuBar() {
        Menu fileMenu = new Menu("File");
        MenuItem loadImageItem = new MenuItem("Load Image");
        loadImageItem.setOnAction(e -> controller.handleLoadImage());

        MenuItem saveImageItem = new MenuItem("Save Image");
        saveImageItem.setOnAction(e -> controller.handleSaveImage());

        MenuItem closeImageItem = new MenuItem("Close Image");
        closeImageItem.setOnAction(e -> controller.handleCloseImage());

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> controller.handleExit());
        fileMenu.getItems().addAll(loadImageItem, saveImageItem, closeImageItem, exitItem);

        Menu generateMenu = new Menu("Generate");
        MenuItem histogramItem = new MenuItem("Histogram");
        histogramItem.setOnAction(e -> controller.handleUpdateProcessingWindow(histogramWindow));

        MenuItem contrastItem = new MenuItem("Contrast");
        contrastItem.setOnAction(e -> controller.handleUpdateProcessingWindow(contrastWindow));

        MenuItem saturationItem = new MenuItem("Hue/Saturation");
        saturationItem.setOnAction(e -> controller.handleUpdateProcessingWindow(saturationWindow));

        MenuItem cropItem = new MenuItem("Crop");
        cropItem.setOnAction(e -> controller.handleUpdateProcessingWindow(cropWindow));
        generateMenu.getItems().addAll(histogramItem, contrastItem, saturationItem, cropItem);

        return(new MenuBar(fileMenu, generateMenu));
    }

    public void displayImage(Image image) {
        imageView.setImage(image);
        imageView.setSmooth(true);
        imageView.setPreserveRatio(true);
        rightView.getChildren().clear();
        rightView.getChildren().add(imageView);
        rightView.setAlignment(Pos.CENTER);

        rightView.setMinSize(0, 0);
        imageView.fitHeightProperty().bind(rightView.heightProperty());
        imageView.fitWidthProperty().bind(rightView.widthProperty());
    }

    public void openProcessingWindow(ProcessingWindow processingWindow) {
        if(splitPane.getItems().size() == 0)
            return;

        double[] dividerPositions = splitPane.getDividerPositions();
        splitPane.getItems().set(0, processingWindow);
        splitPane.setDividerPosition(0, dividerPositions[0]);
    }

    public void displayApplicationStatus(String msg) {
        applicationStatus.setText(msg);
    }

    public void displayApplicationAlert(Alert.AlertType alertType, String msg) {
        Alert alert = new Alert(alertType);
        alert.setContentText(msg);
        alert.show();
    }

    public void resetWindows() {
        histogramWindow = new HistogramWindow(controller);
        contrastWindow = new ContrastWindow(controller);
        saturationWindow = new SaturationWindow(controller);
        cropWindow = new CropWindow(controller);
    }
}