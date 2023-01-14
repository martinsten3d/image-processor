package se.kth.martsten.lab_4_v2.model;

import java.awt.*;

/**
 * This class can be used to edit the contrast values of a pixel matrix.
 */
public class Contrast implements IProcessor {

    private double window, level;

    /**
     * Constructs a new Contrast object with the specified parameters.
     *
     * @param parameters The parameters which will be used to edit the contrast.
     */
    Contrast(double[] parameters){
        setParameters(parameters);
    }

    /**
     * Constructs a new Contrast object without any given parameters.
     */
    Contrast(){
        this(new double[] { 1, 0 });
    }

    /**
     * Sets the parameters which will be used to edit the contrast.
     *
     * @param parameters An array representing the window and level values.
     */
    @Override
    public void setParameters(double[] parameters) {
        window = parameters[0];
        level = parameters[1];
    }

    /**
     * Calculates the new contrast values of a given pixel matrix with the set window and level values.
     * Returns an edited version of the pixel matrix.
     *
     * @param pixelMatrix The pixel matrix which will be edited.
     * @return The edited version of the pixel matrix.
     */
    @Override
    public int[][] processImage(int[][] pixelMatrix) {
        if(window == 1 && level == 0)
            return pixelMatrix;

        for (int x = 0; x < pixelMatrix.length; x++)
            for (int y = 0; y < pixelMatrix[0].length; y++) {
                int a = (pixelMatrix[x][y] >> 24) & 0xff;
                int r = (pixelMatrix[x][y] >> 16) & 0xff;
                int g = (pixelMatrix[x][y] >> 8) & 0xff;
                int b = pixelMatrix[x][y] & 0xff;
                float[] hsb = Color.RGBtoHSB(r, g, b, null);

                if(hsb[2] <= level)
                    hsb[2] = 0;
                else if(hsb[2] > level && hsb[2] < window)
                    hsb[2] = (float)(1f / window * hsb[2]);
                else
                    hsb[2] = 1;

                if(a > 0)
                    pixelMatrix[x][y] = (a << 24) | Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
            }

        return pixelMatrix;
    }

    /**
     * A String that can be used to present the contrast application status.
     *
     * @return The contrast application status as a String.
     */
    @Override
    public String toString() {
        return "Window: " + (int) (window * 255) + " Level: " + (int) (level * 255);
    }
}