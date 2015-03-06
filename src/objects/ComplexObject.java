package objects;

import java.awt.Graphics2D;
import java.io.Serializable;

import display.Display;
import objects.sections.points.ConnectionBase;

public class ComplexObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6305042325566561099L;
	ConnectionBase objectBase;
	
	public ComplexObject(ConnectionBase base){
		objectBase = base;
	}
	
	public void paint(Graphics2D g) {
		objectBase.paintLines(g);
		if(!Display.screenshot)objectBase.paintPoints(g);
	}
	public ComplexObject clone(){
		return new ComplexObject(objectBase.clone());
	}
	public void checkMouseClick() {
		objectBase.checkIfSelected(0);
	}

}
