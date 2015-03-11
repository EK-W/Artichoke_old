package objects.types.boundObject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import running.MouseHandler;

public class ConnectionBase extends BodyPoint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3116583082329140948L;
	
	public ConnectionBase(Point2D l){
		loc = l;
		pointColor = new Color(255,0,0,150);
	}
	
	

	public void paintLines(Graphics2D g) {
		for(int i=0;i<childLines.size();i++){
			childLines.get(i).paintLines(g);
		}
	}

	@Override
	public void updateAsSelected() {
		loc.setLocation(MouseHandler.mouseLoc);
		for(int i=0;i<childLines.size();i++){
			childLines.get(i).updateLines();
		}
	}
	public ConnectionBase clone(){
	//new ConnectionBase created
	ConnectionBase ret = new ConnectionBase(new Point2D.Double(loc.getX(),loc.getY()));
	//connect clones of this one's children to new bodypoint
	for(int i=0;i<childLines.size();i++){
		ret.connectTo(childLines.get(i).clone());
	}	
	//return new ConnectionBase
	return ret;
	}
	public void updateChildren(){
		for(int i=0;i<childLines.size();i++){
			childLines.get(i).updateLines();
		}
	}
}
