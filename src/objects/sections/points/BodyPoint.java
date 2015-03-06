package objects.sections.points;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.Serializable;
import java.util.ArrayList;

import objects.sections.DoubleBodyPoint;
import running.MouseHandler;


public abstract class BodyPoint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8476342901843240575L;
	protected static final double POINT_RADIUS = 7.5;
	protected static final float POINT_OUTLINE_THICKNESS = 1.5f;
	protected static int selectedHighestDepth =0;
	protected ArrayList<ConnectionPoint> childLines = new ArrayList<ConnectionPoint>();
	protected Point2D loc;

	public BodyPoint connectTo(ConnectionPoint childPoint){
		childLines.add(childPoint);
		childPoint.setChildAttributes(loc);
		return this;
	}
	public BodyPoint connectTo(DoubleBodyPoint childPoint){
		childLines.add(childPoint.left);
		childPoint.left.setChildAttributes(loc);
		childLines.add(childPoint.right);
		childPoint.right.setChildAttributes(loc);
		return this;
	}
	
//	public BodyPoint clone(){
//		//new bodyPoint created
//		BodyPoint ret = new BodyPoint();
//		//set location of new bodypoint to this one's location
////		ret.loc.setLocation(loc);
//		//connect clones of this one's children to new bodypoint
//		for(int i=0;i<childLines.size();i++){
//			ret.connectTo(childLines.get(i).clone());
//		}
//		//return new bodypoint
//		return ret;
//		
//	}
	
	public abstract void updateAsSelected();
	
	public void checkIfSelected(int depth){
		if(depth==0)selectedHighestDepth=-1;
		if(intersects(MouseHandler.mouseLoc)&&depth>selectedHighestDepth){
			selectedHighestDepth = depth;
			MouseHandler.selected = this;
		}
		for(int i=0;i<childLines.size();i++){
			childLines.get(i).checkIfSelected(depth+1);
		}
	}
	public boolean intersects(Point2D p){
		return(Math.sqrt(Math.pow(loc.getX()-p.getX(),2)+Math.pow(loc.getY()-p.getY(),2))<POINT_RADIUS);
	}
	
	
	
	/*Template for recursive methods:
	public void example(){
		//...
		for(int i=0;i<childLines.size();i++){
			childLines.get(i).example();
		}
	}
	and in ConnectionLine:
	
	public void example(){
		childPoint.example();
	}
	*/
}
