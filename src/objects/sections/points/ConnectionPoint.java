package objects.sections.points;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

import objects.sections.AngledLine;
import display.DisplaySettings;
import running.MouseHandler;


public class ConnectionPoint extends BodyPoint implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7829137664621105475L;
	AngledLine line;
	float thickness = 6;
	boolean lineRounded = true;
	Color lineColor = Color.black;
	
	public ConnectionPoint(double angle, double length){
		line = new AngledLine(angle,length);
		loc = line.getEnd();
	}
	public ConnectionPoint setLineGraphics(float thickness, boolean rounded, Color color){
		this.thickness=thickness;
		this.lineRounded=rounded;
		lineColor=color;
		return this;
	}
	public void setChildAttributes(Point2D end) {
		line.setBase(end);
	}
	public ConnectionPoint clone(){
		ConnectionPoint ret = new ConnectionPoint(line.getAngle(),line.getLength()).setLineGraphics(thickness,lineRounded, lineColor);
		ret.loc.setLocation(loc);
		for(int i=0;i<childLines.size();i++){
			ret.connectTo(childLines.get(i).clone());		
		}
		return ret;
	}
	public void paintLines(Graphics2D g){
		g.setColor(lineColor);
		if(lineRounded){
			g.setStroke(new BasicStroke(thickness,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		}else{
			g.setStroke(new BasicStroke(thickness));
		}
		g.draw(line.shape());
		for(int i=0;i<childLines.size();i++){
			childLines.get(i).paintLines(g);
		}
		
	}
	public void paintPoints(Graphics2D g){
		g.setColor(new Color(50,100,255,175));
		g.fill(new Ellipse2D.Double(loc.getX()-POINT_RADIUS,loc.getY()-POINT_RADIUS,POINT_RADIUS*2,POINT_RADIUS*2));
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(POINT_OUTLINE_THICKNESS));
		g.draw(new Ellipse2D.Double(loc.getX()-POINT_RADIUS,loc.getY()-POINT_RADIUS,POINT_RADIUS*2,POINT_RADIUS*2));
		for(int i=0;i<childLines.size();i++){
			childLines.get(i).paintPoints(g);
		}
	}
	@Override
	public void updateAsSelected() {
		double previousAngle = line.getAngle();
		line.pointAt(MouseHandler.mouseLoc);
		for(int i=0;i<childLines.size();i++){
			childLines.get(i).updateLineRotations(line.getAngle()-previousAngle);
		}
	}
	private void updateLineRotations(double da){
		line.changeAngle(da);
		for(int i=0;i<childLines.size();i++){
			childLines.get(i).updateLineRotations(da);
		}
	}
	public void updateLines(){
		line.update();
		for(int i=0;i<childLines.size();i++){
			childLines.get(i).updateLines();
		}
	}
}
