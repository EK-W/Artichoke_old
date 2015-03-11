package objects.types.unboundPolygon;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.Serializable;

import display.Display;
import angledLine.AngledLine;
import objects.types.ObjectBase;
import running.MouseHandler;

public class UnboundPolygon extends ObjectBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 212827053575026975L;
	private Color paintColor = Color.red;
	PolygonPoint[] points;
	public UnboundPolygon(int pointAmount, Point2D point, double radius){
		points = new PolygonPoint[pointAmount];
		AngledLine pointPlacer = new AngledLine(point,0, radius);
		for(int i=0;i<points.length;i++){
			pointPlacer.setAngle((360/pointAmount)*i);
			points[i]=new PolygonPoint(pointPlacer.getEnd());
		}
	}
	public UnboundPolygon setPolyGraphics(Color c){
		paintColor=c;
		return this;
	}
	private UnboundPolygon(PolygonPoint[] setPoints){
		this.points = new PolygonPoint[setPoints.length];
		for(int i=0; i<points.length;i++){
			this.points[i]=setPoints[i].clone();
		}
	}
	
	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.red);
		g.fill(getPolygon());
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(4,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		for(int i=0;i<points.length;i++){
			g.draw(new Line2D.Double(points[i].loc,points[(i+1)%points.length].loc));
		}
		if(!Display.screenshot){
			for(int i=0;i<points.length;i++){
				points[i].paintPoints(g);
			}
		}
	}


	@Override
	public UnboundPolygon clone() {
		return new UnboundPolygon(points);
	}
	
	@Override
	public void checkMouseClick() {
		if(MouseHandler.selected!=null){
			return;
		}
		for(int i=0;i<points.length;i++){
			points[i].checkIfSelected();
			if(MouseHandler.selected!=null){
				break;
			}
		}
	}
	
	private Polygon getPolygon(){
		int[] locX = new int[points.length];
		int[] locY = new int[points.length];
		for(int i=0;i<points.length;i++){
			locX[i]=(int) points[i].loc.getX();
			locY[i]=(int) points[i].loc.getY();
		}
		
		return new Polygon(locX,locY,points.length);
		
	}

}
