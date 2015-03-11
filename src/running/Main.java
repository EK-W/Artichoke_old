package running;

import java.awt.geom.Point2D;
import java.io.File;

import objects.ObjectCreator;
import objects.Slide;
import objects.types.unboundPolygon.UnboundPolygon;
import display.Display;

public class Main {
	public static Display display;
	public static Project project;
	public static MouseHandler mouseHandler = new MouseHandler();
	public static KeyHandler keyHandler = new KeyHandler();
	
	
	public static void main(String[] args){ 
		File folder = new File("projects/");
		if (!folder.exists()){
			folder.mkdir();
			System.out.println("Created projects folder.");
		}
		
		project = new Project("test");
		display = new Display();
		display.addMouseListener(mouseHandler);
		display.addMouseMotionListener(mouseHandler);
		display.addKeyListener(keyHandler);
		//Placeholders	
		if(project.slides.size()==0){
			project.slides.add(new Slide());
			Main.project.slides.get(Main.project.slideNumber).addObject(ObjectCreator.createPerson());
			project.slides.get(Main.project.slideNumber).addObject(new UnboundPolygon(16,new Point2D.Double(400,400),100));
			//Clone test:
			//Main.project.slides.get(Main.project.slideNumber).addObject(Main.project.slides.get(Main.project.slideNumber).Objects.get(0).clone());
		}
	}
}
