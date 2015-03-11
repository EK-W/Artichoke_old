package objects.types.boundObject;

import java.awt.Graphics2D;
import java.io.Serializable;

import display.Display;
import objects.types.ObjectBase;

public class BoundObject extends ObjectBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6305042325566561099L;
	ConnectionBase objectBase;
	
	public BoundObject(ConnectionBase base){
		objectBase = base;
	}
	
	public void paint(Graphics2D g) {
		objectBase.paintLines(g);
		if(!Display.screenshot)objectBase.paintPoints(g);
	}
	public BoundObject clone(){
		return new BoundObject(objectBase.clone());
	}
	public void checkMouseClick() {
		objectBase.checkIfSelected();
	}

}
