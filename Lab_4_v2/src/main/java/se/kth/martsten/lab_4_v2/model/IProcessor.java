package se.kth.martsten.lab_4_v2.model;

/**
 * An interface which is used to describe an image process.
 */
public interface IProcessor {

    /**
     * Sets the parameters which will be used to perform the image process.
     *
     * @param parameters An array representing the parameter values.
     */
    void setParameters(double[] parameters);

    /**
     * Calculates the new values of a given pixel matrix with the set parameters.
     * Returns an edited version of the pixel matrix.
     *
     * @param pixelMatrix The pixel matrix which will be edited.
     * @return The edited version of the pixel matrix.
     */
    int[][] processImage(int[][] pixelMatrix);
}