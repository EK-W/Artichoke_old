package objects;

import java.awt.Graphics2D;

import objects.sections.ConnectionBase;
import objects.sections.ConnectionLine;
import running.Display;
import running.MouseHandler;

public class ComplexObject {
	public ConnectionLine[] connectionLines;
	ConnectionBase objectBase;
	
	public ComplexObject(ConnectionBase base,ConnectionLine... lines){
		objectBase = base;
		connectionLines = lines;
	}
	
	public void checkMouseClick(){
		for(int i=0;i<connectionLines.length;i++){
			if(connectionLines[i].child.intersects(MouseHandler.mouseLoc)){
				MouseHandler.selected=connectionLines[i].child;
				break;
			}
		}
	}

	public void paint(Graphics2D g) {
		objectBase.paintLines(g);
		objectBase.paintSelectables(g);
	}

}
