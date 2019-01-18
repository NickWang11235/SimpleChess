/*
 * This is a practice project for game development using Java swing and swt for purely educational purposes.
 * This game is a replica of the flash game GunMayhem 2 and can be found on the following website.
 * https://armorgames.com/
 */
package simplechess;

/**
 * Loads image and resize if needed.
 * @author nickw
 */
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {
    
    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
        }
        return null;
    }
        
}