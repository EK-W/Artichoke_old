package running;

import gifWriting.GifSequenceWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

public class ImageHandler {
	private static ArrayList<File> toWrite = new ArrayList<File>();
	public static void saveScreen(){
	    BufferedImage image = new BufferedImage(Display.screen.width,Display.screen.height,BufferedImage.TYPE_INT_RGB);
	    Display.screenshot=true;
	    Main.display.paint(image.getGraphics());
	    Display.screenshot=false;
	    File file = new File("Slides/Slide"+Display.currentSlide+".png");
	    try {
			ImageIO.write(image, "png", file);
			toWrite.add(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void saveToGif(){
		if(toWrite.isEmpty()){
			System.err.println("No slides saved yet");
			System.exit(1);
		}
		try {
			BufferedImage firstImage = ImageIO.read(toWrite.get(0));
			ImageOutputStream output = new FileImageOutputStream(new File("TheMovie.gif"));
			GifSequenceWriter writer = new GifSequenceWriter(output, firstImage.getType(), 1000, true);
			
			writer.writeToSequence(firstImage);
		    for(int i=1; i<toWrite.size()-1; i++) {
		      BufferedImage nextImage = ImageIO.read(toWrite.get(i));
		      writer.writeToSequence(nextImage);
		    }
		    writer.close();
		    output.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
