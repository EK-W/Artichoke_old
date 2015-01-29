package running;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class ImageHandler {
	public static void saveScreen(){
	    BufferedImage image = new BufferedImage(Display.screen.width,Display.screen.height,BufferedImage.TYPE_INT_RGB);
	    Display.screenshot=true;
	    Main.display.paint(image.getGraphics());
	    Display.screenshot=false;
	    File file = new File("Slides/Slide"+Display.currentSlide+".png");
	    Display.currentSlide++;
	    try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
