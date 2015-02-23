package objects;

import java.io.Serializable;

import objects.sections.BodyPoint;
import objects.sections.ConnectionLine;

public class DoubleBodyLine implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2727820776727512502L;
	public ConnectionLine left;
	public ConnectionLine right;
	
	public DoubleBodyLine(DoubleBodyPoint b, DoubleBodyPoint c,float length,double angle){
		left = new ConnectionLine(b.left,c.left,length,angle*-1);
		right = new ConnectionLine(b.right,c.right,length,angle);
	}
	public DoubleBodyLine(BodyPoint b, DoubleBodyPoint c,float length,double angle){
		left = new ConnectionLine(b,c.left,length,angle*-1);
		right = new ConnectionLine(b,c.right,length,angle);
	}
	public ConnectionLine[] both(){
		return new ConnectionLine[]{left,right};
	}
}
