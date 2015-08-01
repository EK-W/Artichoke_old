package bodies;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;


//Slide is a body that contains all other bodies
public class Slide {
	//ArrayList because it will be referenced often. It will also be added to often so maybe this is a bad idea...
	private static ArrayList<Slide> slides = new ArrayList<Slide>();
	//ArrayList because it wont be added to often but will be referenced multiple times
	private ArrayList<Body> bodies;
	private static int slideNum = 0;
	
	static {
		addSlide();
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
		addSlide(slides.size());
	}
	
	public static void addSlide(int index){
		if(index > slides.size() || index < 0) throw new IndexOutOfBoundsException();
		if(index == 0){
			slides.add(index, new Slide(new ArrayList<Body>()));
		}else{	
			slides.add(index, new Slide(slides.get(index - 1).bodies));
		}
		slideNum = index;
	}
	
	public static void addBody(Body b){
		slides.get(slideNum).bodies.add(b);
	}
	
	private Slide(ArrayList<Body> bodies){
		this.bodies = (ArrayList<Body>) bodies.clone();
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
			Composite c = g.getComposite();
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
			for(Body i:slides.get(slideNum - 1).bodies){
				i.paint(g);
			}
			g.setComposite(c);
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
}
