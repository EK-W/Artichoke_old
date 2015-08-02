package bodies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import running.Main;


//Slide is a body that contains all other bodies
public class Slide{
	//ArrayList because it will be referenced often. It will also be added to often so maybe this is a bad idea...
	private static ArrayList<Slide> slides = new ArrayList<Slide>();
	//ArrayList because it wont be added to often but will be referenced multiple times
	private ArrayList<Body> bodies;
	private static int slideNum = 0;
	
	static {
		addSlide();
	}
	
	public static int getSlideAmt(){
		return slides.size();
	}
	
	public static void setSlide(int to){
		if(to >= slides.size()){
			to = slides.size() - 1;
		}
		if(to < 0){
			to = 0;
		}
		slideNum = to;
	}
	
	public static int getSlideNum(){
		return slideNum;
	}
	
	public static void addSlide(){
		slideNum = slides.size();
		if(slides.size() == 0){
			slides.add(new Slide(new ArrayList<Body>()));
		}else{	
			slides.add(new Slide((ArrayList<Body>) slides.get(slides.size() - 1).bodies));
		}
	}
	
	public static void addBody(Body b){
		slides.get(slideNum).bodies.add(b);
	}
	
	private Slide(ArrayList<Body> toAdd){
		this.bodies = new ArrayList<Body>(toAdd.size());
//		for(Body i: toAdd){
//			addBody((Body) i.clone());
//		}
		for(int i = 0; i < toAdd.size(); i++){
			this.bodies.add((Body) toAdd.get(i).clone());
		}
		//this.bodies = (ArrayList<Body>) toAdd.clone();
	}
	
	public static void checkSelected(){
		for(Body i:slides.get(slideNum).bodies){
			if(i.checkSelected()){
				return;
			}
		}
	}
	
	public static void paint(Graphics2D g){
		if(slideNum > 0){
			for(Body i:slides.get(slideNum - 1).bodies){
				i.paintImg(g);
			}
			g.setColor(new Color(1f, 1f, 1f, 0.5f));
			g.fillRect(0, 0, (int)Main.baseRes.getWidth(), (int)Main.baseRes.getHeight());
		}
		for(Body i:slides.get(slideNum).bodies){
			i.paint(g);
		}
	}
	
	public static void paintImg(Graphics2D g){
		for(Body i:slides.get(slideNum).bodies){
			i.paintImg(g);
		}
	}
	
	public static BufferedImage[] toImgArray(){
		BufferedImage[] ret = new BufferedImage[slides.size()];
		for(int i = 0; i < ret.length; i++){
			ret[i] = new BufferedImage((int)Main.baseRes.getWidth(), (int)Main.baseRes.getHeight(), BufferedImage.TYPE_INT_RGB);
		}
		for(int i = 0; i < slides.size(); i++){
			Graphics2D g = (Graphics2D) ret[i].getGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, (int)Main.baseRes.getWidth(), (int)Main.baseRes.getHeight());
			for(int j = 0; j < slides.get(i).bodies.size(); j++){
				slides.get(i).bodies.get(j).paintImg(g);
			}
		}
		return ret;
	}
}
