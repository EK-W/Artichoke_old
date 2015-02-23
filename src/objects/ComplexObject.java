package objects;

import java.awt.Graphics2D;
import java.io.Serializable;

import display.Display;
import objects.sections.ConnectionBase;
import objects.sections.ConnectionLine;
import running.MouseHandler;

public class ComplexObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6305042325566561099L;
	public ConnectionLine[] connectionLines;
	ConnectionBase objectBase;
	
	public ComplexObject(ConnectionBase base,ConnectionLine... lines){
		objectBase = base;
		connectionLines = lines;
		indexDepth();
	}
//	private ComplexObject(ConnectionBase base){
//		objectBase = base;
//	}
	
//	public ComplexObject clone(){
//		ComplexObject ret = new ComplexObject(objectBase.clone());
//		ret.connectionLines = new ConnectionLine[connectionLines.length];
//		for(int i=0;i<connectionLines.length;i++){
//			ret.connectionLines[i]=connectionLines[i].clone();
//		}
//		ret.indexDepth();
//	}
	
	
	private void indexDepth(){
		objectBase.indexDepth(0);
	}
	
	public void checkMouseClick(){
		for(int i=0;i<connectionLines.length;i++){
			if(connectionLines[i].child.intersects(MouseHandler.mouseLoc)&&connectionLines[i].child.isSelectable()){
				if(MouseHandler.selected==null||connectionLines[i].child.depth>MouseHandler.selected.depth){
					MouseHandler.selected=connectionLines[i].child;
				}
			}
		}
		if(objectBase.intersects(MouseHandler.mouseLoc)&&MouseHandler.selected==null){
			MouseHandler.selected=objectBase;
		}
	}

	public void paint(Graphics2D g) {
		objectBase.paintLines(g);
		if(!Display.screenshot)objectBase.paintSelectables(g);
	}

}
