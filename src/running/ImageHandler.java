package running;

import gifWriting.GifSequenceWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

public class ImageHandler {
//	private static ArrayList<File> toWrite = new ArrayList<File>();
//	public static void saveScreen(){
//	    BufferedImage image = new BufferedImage(Display.screen.width,Display.screen.height,BufferedImage.TYPE_INT_RGB);
//	    Display.screenshot=true;
//	    Main.display.paint(image.getGraphics());
//	    Display.screenshot=false;
//	    File file = new File("projects/"+Main.project.projectName+"/slides/slide"+Main.project.slideNumber+".png");
//	    try {
//			ImageIO.write(image, "png", file);
//			//toWrite.add(file);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	public static void saveToGif(){
//		if(toWrite.isEmpty()){
//			System.err.println("No slides saved yet");
//			System.exit(1);
//		}
		try {
			File slides = new File("projects/"+Main.project.projectName+"/slides");
			if(slides.listFiles().length<1){
				System.err.println("You have no slides yet");
				System.exit(1);
				
			}
			File[] toWrite = new File[slides.listFiles().length];
			for(int i=0;i<toWrite.length;i++){
				toWrite[i]=new File("projects/"+Main.project.projectName+"/slides/Slide"+(i+1)+".png");
			}
			BufferedImage firstImage = ImageIO.read(toWrite[0]);
			ImageOutputStream output = new FileImageOutputStream(new File("projects/"+Main.project.projectName+"/TheMovie.gif"));
			GifSequenceWriter writer = new GifSequenceWriter(output, firstImage.getType(), 100, true);
			
			writer.writeToSequence(firstImage);
		    for(int i=1; i<toWrite.length; i++) {
		      BufferedImage nextImage = ImageIO.read(toWrite[i]);
		      writer.writeToSequence(nextImage);
		    }
		    writer.close();
		    output.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
