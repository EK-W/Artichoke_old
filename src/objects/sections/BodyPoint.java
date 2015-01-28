package objects.sections;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BodyPoint {
	public int index;
	private static int indexCounter = 0;
	protected static final float radius = 7.5f;
	protected static final float outlineSize = 1.5f;
	protected ConnectionLine parentLine;
	protected Point2D location;
	public ArrayList<ConnectionLine> children;
	
	public BodyPoint(){
		index = indexCounter;
		indexCounter++;
	}
	public int getIndex() {
		return index;
	}
	
	public void showPoint(Graphics2D g){
		g.setColor(Color.black);
		g.fill(new Ellipse2D.Double(location.getX()-radius, location.getY()-radius, radius*2, radius*2));
		g.fill(new Ellipse2D.Double(location.getX()-(radius-outlineSize), location.getY()-(radius-outlineSize), (radius-outlineSize)*2, (radius-outlineSize)*2));
	}
	public boolean intersects(Point2D p){
		return(Math.sqrt(Math.pow(location.getX()-p.getX(),2)+Math.pow(location.getY()-p.getY(),2))<radius);
	}
	public void paintLines(Graphics2D g){
		if(children!=null){
			for(int i=0;i<children.size();i++){
				children.get(i).paintLine(g);
				children.get(i).child.paintLines(g);
			}
		}
	}
	public void paintSelectables(Graphics2D g){
		showPoint(g);
		if(children!=null){
			for(int i=0;i<children.size();i++){
				children.get(i).child.paintSelectables(g);
			}
		}
	}
}
