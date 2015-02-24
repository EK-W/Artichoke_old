package running;

import gifWriting.GifSequenceWriter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

import display.Display;
import objects.Slide;

public class Project implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5369364193822336014L;
	public String projectName;
	public ArrayList<Slide> slides = new ArrayList<Slide>();
	public int slideNumber = 0;
	
	public void drawPreviousSlide(Graphics2D g){
		if(slideNumber!=0){
			Display.screenshot=true;
			slides.get(slideNumber-1).paint(g);
			Display.screenshot=false;
		}
	}
	
	public void changeSlide(boolean increase){
		if(slides.size()<2){
			return;
		}
		if(!increase && slideNumber>0){
			slideNumber--;
		}
		if(increase && slideNumber<slides.size()-1){
			slideNumber++;
		}
	}
	public void setSlide(int newNum){
		if(slides.size()<2){
			return;
		}
		if(newNum>-1){
			slideNumber=newNum;
		}
		if(newNum<slides.size()){
			slideNumber=newNum;
		}
	}
	
	public void save(){
		this.save(100);
	}
	
	public void save(int gifSpeed){		
		Display.screenshot=true;
		BufferedImage toAdd = new BufferedImage(Display.screen.width,Display.screen.height,BufferedImage.TYPE_INT_RGB);
		int prevSlideNumber=slideNumber;
		GifSequenceWriter writer = null;
		ImageOutputStream output;
		new File("projects/"+projectName+"/"+projectName+".gif").delete();
		try {
		output = new FileImageOutputStream(new File("projects/"+projectName+"/"+projectName+".gif"));
		writer = new GifSequenceWriter(output, BufferedImage.TYPE_INT_RGB, gifSpeed, true);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for(slideNumber=0;slideNumber<slides.size();slideNumber++){
		    Main.display.paint(toAdd.getGraphics());
		    File file = new File("projects/"+projectName+"/slides/slide"+slideNumber+".png");
		    try {
				ImageIO.write(toAdd, "png", file);
				writer.writeToSequence(toAdd);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Display.screenshot=false;
		slideNumber=prevSlideNumber;
		try {
			FileOutputStream fileOut = new FileOutputStream("projects/"+projectName+"/"+projectName+".txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			         
	}
	
	public Project(String folderName){
		projectName=folderName;
		slides.clear();
		File folder = new File("projects/"+folderName);
		if (folder.exists()){
			if(new File (folder.toString()+"/"+folderName+".txt").exists()){
				deserialize();
			}
		}else{
			folder.mkdir();
			System.out.println("Created new project: projects/"+folderName);
		}
		File slidesLoc = new File(folder.toString()+"/slides");
		if(!slidesLoc.exists()){
			slidesLoc.mkdir();
			System.out.println("Created new folder: projects/"+folderName+"/slides");
		}
//		File sit = new File(folder.toString()+"/situations");
//		if(!sit.exists()){
//			sit.mkdir();
//			System.out.println("Created new folder: projects/"+folderName+"/situations");
//		}
	}

	private void deserialize() {
		try {
			FileInputStream fileIn;
			fileIn = new FileInputStream("projects/"+projectName+"/"+projectName+".txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
	        this.slides = ((Project)(in.readObject())).slides;
	        in.close();
	        fileIn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void addNewSlide() {
		slides.add(slides.get(slides.size()-1).clone());
	}
}