package se.kth.martsten.lab_4_v2.view;

import javafx.geometry.Insets;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import java.io.File;

public class HistogramWindow extends ProcessingWindow {

    private XYChart.Series<Number, Number> seriesRed, seriesGreen, seriesBlue;

    HistogramWindow(Controller controller) {
        super(controller);
    }

    @Override
    protected void createNodes() {
        this.setPadding(new Insets(20));
        this.setSpacing(10);

        seriesRed = new XYChart.Series<>();
        seriesGreen = new XYChart.Series<>();
        seriesBlue = new XYChart.Series<>();
        seriesRed.setName("Red");
        seriesGreen.setName("Green");
        seriesBlue.setName("Blue");

        final NumberAxis xAxis = new NumberAxis(0, 255, 8);
        final NumberAxis yAxis = new NumberAxis();
        AreaChart<Number, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setTitle("Histogram");
        File f = new File("src/css/Histogram.css");
        areaChart.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        areaChart.getData().addAll(seriesRed, seriesGreen, seriesBlue);

        Button buttonUpdateHistogram = new Button("Update");
        buttonUpdateHistogram.setOnAction(event -> controller.handleGenerateHistogram());

        this.getChildren().addAll(areaChart, buttonUpdateHistogram);
    }

    public void displayHistogram(int[][] histogramData) {
        seriesRed.getData().clear();
        seriesGreen.getData().clear();
        seriesBlue.getData().clear();
        for (int i = 0; i < 256; i++) {
            seriesRed.getData().add(new XYChart.Data<>(i, histogramData[0][i]));
            seriesGreen.getData().add(new XYChart.Data<>(i, histogramData[1][i]));
            seriesBlue.getData().add(new XYChart.Data<>(i, histogramData[2][i]));
        }
    }
}