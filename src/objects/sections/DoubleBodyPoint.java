package objects.sections;

import java.io.Serializable;

import objects.sections.points.ConnectionPoint;

public class DoubleBodyPoint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2427070170768951412L;
	public ConnectionPoint left;
	public ConnectionPoint right;
	
	public DoubleBodyPoint(double angle,double length){
		left = new ConnectionPoint(angle*-1, length);
		right = new ConnectionPoint(angle,length);
	}
	public void connectTo(DoubleBodyPoint to){
		left.connectTo(to.left);
		right.connectTo(to.right);
	}
}
