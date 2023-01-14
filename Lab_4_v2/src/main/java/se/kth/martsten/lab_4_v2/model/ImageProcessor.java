package se.kth.martsten.lab_4_v2.model;

/**
 * This class represents an image processor which defines the different image processing methods that
 * can be used to edit a pixel matrix.
 */
@SuppressWarnings("ManualArrayCopy")
public class ImageProcessor {

    private int[][] originalPixelMatrix, processedPixelMatrix;
    private String applicationStatus;

    private Contrast contrast;
    private Saturation saturation;
    private Crop crop;

    /**
     * Constructs a new image processor with an empty pixel matrix.
     */
    public ImageProcessor() {
        loadNewPixelMatrix(new int[0][]);
    }

    /**
     * Loads a new pixel matrix into the image processor.
     *
     * @param newPixelMatrix The new pixel matrix.
     */
    public void loadNewPixelMatrix(int[][] newPixelMatrix) {
        this.originalPixelMatrix = newPixelMatrix;
        this.processedPixelMatrix = newPixelMatrix;

        contrast = new Contrast();
        saturation = new Saturation();
        crop = new Crop();

        applicationStatus = "";
    }

    public int[][] getOriginalPixelMatrix() {
        if(originalPixelMatrix.length == 0)
            return new int[0][];

        int[][] test = new int[originalPixelMatrix.length][originalPixelMatrix[0].length];
        for(int x = 0; x < originalPixelMatrix.length; x++)
            for (int y = 0; y < originalPixelMatrix[0].length; y++)
                test[x][y] = originalPixelMatrix[x][y];
        return test;
    }

    public int[][] getProcessedPixelMatrix() {
        return processedPixelMatrix;
    }

    /**
     * Creates a new Histogram object that is used to calculate the histogram data of the processed pixel matrix.
     *
     * @return Histogram data of the processed pixel matrix.
     */
    public int[][] calculateHistogram() {
        Histogram histogram = new Histogram();
        applicationStatus = histogram.toString();
        return histogram.calculateHistogram(getProcessedPixelMatrix());
    }

    /**
     * Performs a new image process. The original pixel matrix will firstly get edited with the set crop values, then the cropped
     * pixel matrix will get edited with the set contrast values and lastly that pixel matrix will be edited with the set HSB values.
     *
     * @param params The processing parameters of a specific image process.
     */
    public void processImage(ProcessingParams params) {
        IProcessor processor = null;
        switch (params.getType()){
            case CONTRAST -> processor = contrast;
            case SATURATION -> processor = saturation;
            case CROP -> processor = crop;
        }

        if(processor == null) return;

        processor.setParameters(params.getParameters());
        int[][] croppedPixelMatrix = crop.processImage(getOriginalPixelMatrix());
        int[][] contrastedPixelMatrix = contrast.processImage(croppedPixelMatrix);
        processedPixelMatrix = saturation.processImage(contrastedPixelMatrix);
        applicationStatus = processor.toString();
    }

    /**
     * A String that can be used to present the application status.
     *
     * @return The application status as a String.
     */
    @Override
    public String toString() {
        return applicationStatus;
    }
}