package se.kth.martsten.lab_4_v2.view;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import se.kth.martsten.lab_4_v2.ImagePixelMatrixConverter;
import se.kth.martsten.lab_4_v2.io.FileIO;
import se.kth.martsten.lab_4_v2.model.ImageProcessor;
import se.kth.martsten.lab_4_v2.model.ProcessType;
import se.kth.martsten.lab_4_v2.model.ProcessingParams;

import java.io.IOException;

public class Controller {

    private final ImageProcessor imageProcessor;
    private final View view;
    private final FileIO fileIO;

    public Controller(View view, ImageProcessor imageProcessor) {
        this.imageProcessor = imageProcessor;
        this.view = view;
        fileIO = new FileIO();
    }

    public void handleLoadImage() {
        Image image = fileIO.loadImageFile();
        if(image == null) return;

        handleCloseImage();

        int[][] pixelMatrix = ImagePixelMatrixConverter.getPixelMatrix(image);
        imageProcessor.loadNewPixelMatrix(pixelMatrix);
        view.displayImage(ImagePixelMatrixConverter.getImage(imageProcessor.getProcessedPixelMatrix()));
    }

    public void handleSaveImage() {
        Image image = ImagePixelMatrixConverter.getImage(imageProcessor.getProcessedPixelMatrix());
        if(image == null) {
            view.displayApplicationAlert(Alert.AlertType.INFORMATION, "No image loaded.");
            return;
        }

        try { fileIO.saveImageFile(image); }
        catch(IOException e) { view.displayApplicationAlert(Alert.AlertType.ERROR,"Something went wrong when saving the image."); }
    }

    public void handleCloseImage() {
        imageProcessor.loadNewPixelMatrix(new int[0][]);
        view.displayImage(ImagePixelMatrixConverter.getImage(imageProcessor.getProcessedPixelMatrix()));
        view.displayApplicationStatus(imageProcessor.toString());
        view.resetWindows();
        handleUpdateProcessingWindow(null);
    }

    public void handleExit() {
        System.exit(0);
    }

    public void handleUpdateProcessingWindow(ProcessingWindow processingWindow) {
        if(processingWindow == null) {
            processingWindow = view.emptyWindow;
        }
        else if(imageProcessor.getOriginalPixelMatrix().length == 0 || imageProcessor.getOriginalPixelMatrix()[0].length == 0) {
            view.displayApplicationAlert(Alert.AlertType.INFORMATION, "No image loaded.");
            processingWindow = view.emptyWindow;
        }

        view.openProcessingWindow(processingWindow);
    }

    public void handleGenerateHistogram() {
        view.histogramWindow.displayHistogram(imageProcessor.calculateHistogram());
        view.displayApplicationStatus(imageProcessor.toString());
    }

    public void handleContrastProcessing(double window, double level) {
        imageProcessor.processImage(new ProcessingParams(ProcessType.CONTRAST, new double[]{ window, level }));
        updateView();
    }

    public void handleSaturationProcessing(double hue, double saturation, double brightness) {
        imageProcessor.processImage(new ProcessingParams(ProcessType.SATURATION, new double[]{ hue, saturation, brightness }));
        updateView();
    }

    public void handleCropProcessing(double top, double bottom, double left, double right) {
        imageProcessor.processImage(new ProcessingParams(ProcessType.CROP, new double[]{ top, bottom, left, right }));
        updateView();
    }

    private void updateView() {
        view.displayImage(ImagePixelMatrixConverter.getImage(imageProcessor.getProcessedPixelMatrix()));
        view.displayApplicationStatus(imageProcessor.toString());
    }
}