package objects.types.unboundPolygon;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

import objects.types.PointBase;
import running.MouseHandler;
import display.DisplaySettings;

public class PolygonPoint extends PointBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1097093540379454490L;
	
	public PolygonPoint(Point2D loc) {
		this.loc.setLocation(loc);
		pointColor = new Color(50,100,255,175);
	}
	public PolygonPoint clone(){
		return new PolygonPoint(loc);
	}
	public void checkIfSelected() {
		if(intersects(MouseHandler.mouseLoc)){
			MouseHandler.selected = this;
		}
	}
	public boolean intersects(Point2D p){
		return(Math.sqrt(Math.pow(loc.getX()-p.getX(),2)+Math.pow(loc.getY()-p.getY(),2))<DisplaySettings.POINT_RADIUS);
	}

	@Override
	public void updateAsSelected() {
		loc.setLocation(MouseHandler.mouseLoc);
	}
}
