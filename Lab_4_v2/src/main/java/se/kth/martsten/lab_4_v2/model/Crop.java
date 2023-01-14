package se.kth.martsten.lab_4_v2.model;

/**
 * This class can be used to crop the size of a pixel matrix.
 */
@SuppressWarnings("ManualArrayCopy")
public class Crop implements IProcessor {

    private double top, bottom, left, right;

    private int width, height;

    /**
     * Constructs a new Crop object with the specified parameters.
     *
     * @param parameters The parameters which will be used to crop the pixel matrix.
     */
    Crop(double[] parameters) {
        setParameters(parameters);
    }

    /**
     * Constructs a new Crop object without any given parameters.
     */
    Crop() {
        this(new double[] { 0, 0, 0, 0 });
    }

    /**
     * Sets the parameters which will be used to crop the pixel matrix.
     *
     * @param parameters An array representing the top, bottom, left and right values which is used to crop the pixel matrix.
     */
    @Override
    public void setParameters(double[] parameters) {
        top = parameters[0];
        bottom = parameters[1];
        left = parameters[2];
        right = parameters[3];
    }

    /**
     * Calculates a new size of a given pixel matrix with the set top, bottom, left and right values.
     * Returns an edited version of the pixel matrix.
     *
     * @param pixelMatrix The pixel matrix which will be edited.
     * @return The edited version of the pixel matrix.
     */
    @Override
    public int[][] processImage(int[][] pixelMatrix) {
        width = pixelMatrix.length - (int)((double)pixelMatrix.length * left) - (int)((double)pixelMatrix.length * right);
        height = pixelMatrix[0].length - (int)((double)pixelMatrix[0].length * top) - (int)((double)pixelMatrix[0].length * bottom);

        if(top == 0 && bottom == 0 && left == 0 && right == 0)
            return pixelMatrix;

        if(width <= 0) width = 0;
        if(height <= 0) height = 0;

        int[][] cropped = new int[width][height];
        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++)
                cropped[x][y] = pixelMatrix[x + (int)((double)pixelMatrix.length * left)][y + (int)((double)pixelMatrix[0].length * top)];

        return cropped;
    }

    /**
     * A String that can be used to present the crop application status.
     *
     * @return The crop application status as a String.
     */
    @Override
    public String toString(){
        return "Width: " + width + " px Height: " + height + " px";
    }
}