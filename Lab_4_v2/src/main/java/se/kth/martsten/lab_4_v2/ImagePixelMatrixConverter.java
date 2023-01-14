package se.kth.martsten.lab_4_v2;

import javafx.scene.image.*;

public class ImagePixelMatrixConverter {

    public static int[][] getPixelMatrix(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        int[][] pixelMatrix = new int[width][height];
        PixelReader reader = image.getPixelReader();

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                pixelMatrix[x][y] = reader.getArgb(x, y);

        return pixelMatrix;
    }

    public static Image getImage(int[][] pixelMatrix) {
        if(pixelMatrix.length < 1 || pixelMatrix[0].length < 1)
            return null;

        WritableImage writableImage = new WritableImage(pixelMatrix.length, pixelMatrix[0].length);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int x = 0; x < pixelMatrix.length; x++)
            for (int y = 0; y < pixelMatrix[0].length; y++)
                pixelWriter.setArgb(x, y, pixelMatrix[x][y]);

        return writableImage;
    }
}