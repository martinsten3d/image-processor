package se.kth.martsten.lab_4_v2.io;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileIO {

    public Image loadImageFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.bmp"));

        File file = fileChooser.showOpenDialog(null);
        if(file == null) return null;

        return new Image(file.toURI().toString());
    }

    public void saveImageFile(Image image) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPEG", ".jpg"),
                new FileChooser.ExtensionFilter("PNG", ".png"),
                new FileChooser.ExtensionFilter("BMP", ".bmp"));
        File file = fileChooser.showSaveDialog(null);
        if(file == null) return;

        BufferedImage buffered = new BufferedImage((int)image.getWidth(), (int)image.getHeight(), BufferedImage.TYPE_INT_RGB);
        ImageIO.write(
                SwingFXUtils.fromFXImage(image, buffered),
                fileChooser.getSelectedExtensionFilter().getExtensions().get(0).substring(1),
                file);
    }
}