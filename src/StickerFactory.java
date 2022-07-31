import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class StickerFactory {

    public void generate(InputStream inputStream, String fileName) throws Exception {
        // Image reading - in this example from a local directory
        // BufferedImage originalImage = ImageIO.read(new File("image/test-poster.jpg"));

        // Image reading - on a more general type of file read
        // InputStream inputStream = new FileInputStream(new File("image/test-poster.jpg"));

        // Image reading - from an url
        //InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);

        // Creating a new image
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;
        // Creating a new image with the translucent back
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // Copying the original image to new image
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // Font configuration
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 80);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        // Writing a phrase on the image
        graphics.drawString("AWESOME!", 100, 1100);

        // Writing the new image on a file - saving on a local directory
        // ImageIO.write(newImage, "png", new File("image/sticker.png"));
        ImageIO.write(newImage, "png", new File(fileName));
    }

    // Using the main below when a test for generate stickers are necessary
//    public static void main(String[] args) throws Exception {
//
//        var stickerGenerate = new StickerFactory();
//        stickerGenerate.generate();
//    }
}
