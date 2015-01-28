package objects;

import java.awt.geom.Point2D;

import objects.sections.ConnectionBase;
import objects.sections.ConnectionLine;
import objects.sections.ConnectionPoint;

public class ObjectCreator {
	
	public static void createPerson(){
		ConnectionBase pointWaist = new ConnectionBase(new Point2D.Double(640,500));
		ConnectionPoint pointShoulderCenter = new ConnectionPoint();
		ConnectionPoint pointShoulderLeft = new ConnectionPoint();
		ConnectionPoint pointShoulderRight = new ConnectionPoint();
		ConnectionPoint pointArmTopLeft = new ConnectionPoint();
		ConnectionPoint pointArmTopRight = new ConnectionPoint();
		ConnectionPoint pointArmBottomLeft = new ConnectionPoint();
		ConnectionPoint pointArmBottomRight = new ConnectionPoint();
		
		ConnectionPoint pointLegTopLeft = new ConnectionPoint();
		ConnectionPoint pointLegTopRight = new ConnectionPoint();
		ConnectionPoint pointLegBottomLeft = new ConnectionPoint();
		ConnectionPoint pointLegBottomRight = new ConnectionPoint();
		ConnectionPoint pointFootLeft = new ConnectionPoint();
		ConnectionPoint pointFootRight = new ConnectionPoint();
		
		ConnectionLine torso = new ConnectionLine(pointWaist,pointShoulderCenter,150,0);
		ConnectionLine shoulderLeft = new ConnectionLine(pointShoulderCenter,pointShoulderLeft,40,-110);
		ConnectionLine shoulderRight = new ConnectionLine(pointShoulderCenter,pointShoulderRight,40,110);
		ConnectionLine armTopLeft = new ConnectionLine(pointShoulderLeft,pointArmTopLeft,60,180);
		ConnectionLine armTopRight = new ConnectionLine(pointShoulderRight,pointArmTopRight,60,180);
		ConnectionLine armBottomLeft = new ConnectionLine(pointArmTopLeft,pointArmBottomLeft,50,180);
		ConnectionLine armBottomRight = new ConnectionLine(pointArmTopRight,pointArmBottomRight,50,180);
		
		ConnectionLine legTopLeft = new ConnectionLine(pointWaist,pointLegTopLeft,75,-150);
		ConnectionLine legTopRight = new ConnectionLine(pointWaist,pointLegTopRight,75,150);
		ConnectionLine legBottomLeft = new ConnectionLine(pointLegTopLeft,pointLegBottomLeft,60,180);
		ConnectionLine legBottomRight = new ConnectionLine(pointLegTopRight,pointLegBottomRight,60,180);
		ConnectionLine footLeft = new ConnectionLine(pointLegBottomLeft,pointFootLeft,25,-90);
		ConnectionLine footRight = new ConnectionLine(pointLegBottomRight,pointFootRight,25,90);
		
		
		ComplexObject person = new ComplexObject(pointWaist,torso,shoulderLeft,shoulderRight,armTopLeft,armTopRight,armBottomLeft,armBottomRight,
				legTopLeft,legTopRight,legBottomLeft,legBottomRight,footLeft,footRight);
		ObjectRegistry.addObject(person);
	}
}
