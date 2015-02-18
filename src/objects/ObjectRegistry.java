package objects;

import java.awt.Graphics2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import running.Display;
import running.MouseHandler;

public class ObjectRegistry {
	private static ArrayList<ComplexObject> Objects = new ArrayList<ComplexObject>();
	
	public static void paint(Graphics2D g){
		for(int i=0;i<Objects.size();i++){
			Objects.get(i).paint(g);
		}
	}
	
	public static void checkMouseClick(){	
		for(int i=0;i<Objects.size();i++){
			if(MouseHandler.selected==null){
				Objects.get(i).checkMouseClick();
			}else{
				break;
			}
		}
	}
	public static void addObject(ComplexObject e){
		Objects.add(e);
	}
	
	public static void serialize(){			 
		try {
			FileOutputStream fileOut = new FileOutputStream("Slides/Slide" + Display.currentSlide+".txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(Objects.get(0));
			out.close();
			fileOut.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			         
	}
}
