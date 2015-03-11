package objects;

import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import objects.types.ObjectBase;
import objects.types.boundObject.BoundObject;
import running.Main;
import running.MouseHandler;

public class Slide implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7136195893435459941L;
	public ArrayList<ObjectBase> Objects = new ArrayList<ObjectBase>();
	
	
	public void paint(Graphics2D g){
		for(int i=0;i<Objects.size();i++){
			Objects.get(i).paint(g);
		}
	}
	
	public void checkMouseClick(){	
		for(int i=0;i<Objects.size();i++){
			if(MouseHandler.selected==null){
				Objects.get(i).checkMouseClick();
			}else{
				break;
			}
		}
	}
	public void addObject(ObjectBase e){
		Objects.add(e);
	}
	public Slide clone(){
		//Slide ret = null;
		Slide ret = new Slide();
		for(int i=0;i<Objects.size();i++){
			ret.Objects.add(Objects.get(i).clone());
		}
		
//		try {
//			FileOutputStream fileOut = new FileOutputStream("projects/"+Main.project.projectName+"/temp.txt");
//			ObjectOutputStream out = new ObjectOutputStream(fileOut);
//			out.writeObject(this);
//			out.close();
//			fileOut.close();
//			FileInputStream fileIn = new FileInputStream("projects/"+Main.project.projectName+"/temp.txt");
//	        ObjectInputStream in = new ObjectInputStream(fileIn);
//	        ret = (Slide) in.readObject();
//	        in.close();
//	        fileIn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return ret;
	}
	
}
