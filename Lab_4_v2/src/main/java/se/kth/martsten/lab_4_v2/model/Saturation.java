package se.kth.martsten.lab_4_v2.model;

import java.awt.*;

/**
 * This class can be used to edit the hue, saturation and brightness (HSB) of a pixel matrix.
 */
public class Saturation implements IProcessor {

    private double hue, saturation, brightness;

    /**
     * Constructs a new Saturation object with the specified parameters.
     *
     * @param parameters The parameters which will be used to edit the HSB.
     */
    Saturation(double[] parameters) {
        setParameters(parameters);
    }

    /**
     * Constructs a new Saturation object without any given parameters.
     */
    Saturation() {
        this(new double[] { 0.5, 0.5, 0.5 });
    }

    /**
     * Sets the parameters which will be used to edit the HSB.
     *
     * @param parameters An array representing the HSB values.
     */
    @Override
    public void setParameters(double[] parameters) {
        hue = parameters[0];
        saturation = parameters[1];
        brightness = parameters[2];
    }

    /**
     * Calculates the new HSB values of a given pixel matrix with the set hue, saturation and brightness values.
     * Returns an edited version of the pixel matrix.
     *
     * @param pixelMatrix The pixel matrix which will be edited.
     * @return The edited version of the pixel matrix.
     */
    @Override
    public int[][] processImage(int[][] pixelMatrix) {
        if(hue == 0.5 && saturation == 0.5 && brightness == 0.5)
            return pixelMatrix;

        for (int x = 0; x < pixelMatrix.length; x++)
            for (int y = 0; y < pixelMatrix[0].length; y++) {
                int a = (pixelMatrix[x][y] >> 24) & 0xff;
                int r = (pixelMatrix[x][y] >> 16) & 0xff;
                int g = (pixelMatrix[x][y] >> 8) & 0xff;
                int b = pixelMatrix[x][y] & 0xff;

                float[] hsb = Color.RGBtoHSB(r, g, b, null);
                hsb[0] += (float)hue;

                hsb[1] = hsb[1] * ((float)saturation + 0.5f) * 2;
                if(hsb[1] > 1) hsb[1] = 1;

                hsb[2] = hsb[2] * ((float)brightness + 0.5f) * 2;
                if(hsb[2] > 1) hsb[2] = 1;

                if(a > 0)
                    pixelMatrix[x][y] = (a << 24) | Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
            }

        return pixelMatrix;
    }

    /**
     * A String that can be used to present the saturation/HSB application status.
     *
     * @return The saturation/HSB application status as a String.
     */
    @Override
    public String toString() {
        return "Hue: " + (int)(hue * 360f) + " Saturation: " + (int)(saturation * 200f) + " Brightness: " + (int)(brightness * 200f);
    }
}