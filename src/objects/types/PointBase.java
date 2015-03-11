package objects.types;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

import display.DisplaySettings;

public abstract class PointBase implements Serializable{
	public abstract void updateAsSelected();
	public abstract void checkIfSelected();
	public Point2D loc = new Point2D.Double();
	protected Color pointColor;
	
	
	public boolean intersects(Point2D p){
		return(Math.sqrt(Math.pow(loc.getX()-p.getX(),2)+Math.pow(loc.getY()-p.getY(),2))<DisplaySettings.POINT_RADIUS);
	}
	public void paintPoints(Graphics2D g){
		//g.setColor(new Color(255,0,0,150));
		g.setColor(pointColor);
		g.fill(new Ellipse2D.Double(loc.getX()-DisplaySettings.POINT_RADIUS,loc.getY()-DisplaySettings.POINT_RADIUS,DisplaySettings.POINT_RADIUS*2,DisplaySettings.POINT_RADIUS*2));
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(DisplaySettings.POINT_OUTLINE_THICKNESS));
		g.draw(new Ellipse2D.Double(loc.getX()-DisplaySettings.POINT_RADIUS,loc.getY()-DisplaySettings.POINT_RADIUS,DisplaySettings.POINT_RADIUS*2,DisplaySettings.POINT_RADIUS*2));
		
	}
}
