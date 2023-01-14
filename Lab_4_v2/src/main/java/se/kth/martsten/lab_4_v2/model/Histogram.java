package se.kth.martsten.lab_4_v2.model;

/**
 * This class represents a Histogram.
 */
@SuppressWarnings("ALL")
public class Histogram {

    Histogram() { }

    /**
     * Returns a 3*256 matrix that represents the RGB colors of a given pixel matrix.
     *
     * @param imageData The given pixel matrix.
     * @return The 3*256 matrix representing RGB colors of the given pixel matrix.
     */
    public int[][] calculateHistogram(int[][] imageData) {
        int[][] histogramData = new int[3][256];
        for (int x = 0; x < imageData.length; x++)
            for (int y = 0; y < imageData[0].length; y++) {
                histogramData[0][(imageData[x][y] >> 16) & 0xff]++;
                histogramData[1][(imageData[x][y] >> 8) & 0xff]++;
                histogramData[2][(imageData[x][y]) & 0xff]++;
            }
        return histogramData;
    }

    /**
     * A String that can be used to present the histogram application status.
     *
     * @return The histogram application status as a String.
     */
    @Override
    public String toString() {
        return "Histogram generated.";
    }
}