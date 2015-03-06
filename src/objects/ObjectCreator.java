package objects;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import objects.sections.DoubleBodyPoint;
import objects.sections.points.ConnectionBase;
import objects.sections.points.ConnectionPoint;

public class ObjectCreator {
	
	public static ComplexObject createPerson(){
		ConnectionBase waist = new ConnectionBase(new Point2D.Double(640,400));
		ConnectionPoint neckBottom = new ConnectionPoint(0,150);
		DoubleBodyPoint leg = new DoubleBodyPoint(135,100);
		DoubleBodyPoint armsTop = new DoubleBodyPoint (135,40);
		DoubleBodyPoint armsBottom = new DoubleBodyPoint (135,40);
		DoubleBodyPoint shoulders = new DoubleBodyPoint (90,20);
		ConnectionPoint neckTop = new ConnectionPoint(0,30);
		ConnectionPoint forehead = new ConnectionPoint(0,25).setLineGraphics(50, true, Color.black);
		
		neckTop.connectTo(forehead);
		waist.connectTo(neckBottom);
		neckBottom.connectTo(shoulders);
		shoulders.connectTo(armsTop);
		armsTop.connectTo(armsBottom);
		neckBottom.connectTo(neckTop);
		
		waist.connectTo(leg);
		waist.updateChildren();
		return new ComplexObject(waist);
	}
	
	
}
