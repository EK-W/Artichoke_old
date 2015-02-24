package objects.sections;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;


public class BodyPoint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8476342901843240575L;
//	public int index;
	public int depth;
//	private static int indexCounter = 0;
	protected static final float radius = 7.5f;
	protected static final float outlineSize = 1.5f;
	protected ConnectionLine parentLine;
	protected Point2D location;
	public ArrayList<ConnectionLine> children;
	
	public BodyPoint(){
//		index = indexCounter;
//		indexCounter++;
	}
	
	public BodyPoint clone(){
//		System.out.print("1");
		BodyPoint ret = new BodyPoint();
		if(parentLine!=null)
		ret.parentLine=parentLine.clone();
		ret.location=new Point2D.Double(location.getX(),location.getY());
//		ret.children = new ArrayList<ConnectionLine>();
//		for(int i=0;i<children.size();i++){
//			ret.children.add(children.get(i).clone());
//		}
		ret.depth=depth;
		return ret;
	}
	
	public void indexDepth(int d){
		depth = d;
		if(children!=null)
		for(int i=0;i<children.size();i++){
			children.get(i).child.indexDepth(d+1);
		}
	}
//	public int getIndex() {
//		return index;
//	}
	
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
	public void updateAsSelected() {
		
	}
}
