package running;

import java.io.File;

import objects.ObjectCreator;
import objects.Slide;
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
		if(Main.project.slides.size()==0){
			Main.project.slides.add(new Slide());
			Main.project.slides.get(Main.project.slideNumber).addObject(ObjectCreator.createPerson());
			//Clone test:
			//Main.project.slides.get(Main.project.slideNumber).addObject(Main.project.slides.get(Main.project.slideNumber).Objects.get(0).clone());
		}
	}
}
