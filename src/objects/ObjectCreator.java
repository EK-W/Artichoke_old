package objects;

import java.awt.Color;
import java.awt.geom.Point2D;

import objects.sections.ConnectionBase;
import objects.sections.ConnectionLine;
import objects.sections.ConnectionPoint;

public class ObjectCreator {
	
	public static void createPerson(){
		ConnectionBase waistPoint = new ConnectionBase(new Point2D.Double(640,500));
		ConnectionPoint forehead = new ConnectionPoint().setUnselectable(true);
		ConnectionPoint throat = new ConnectionPoint();
		
		ConnectionPoint chest = new ConnectionPoint();
		//DoubleBodyPoint shoulderPoint = new DoubleBodyPoint().setUnselectable();
		DoubleBodyPoint shoulderPoint = new DoubleBodyPoint();
		DoubleBodyPoint elbow = new DoubleBodyPoint();
		DoubleBodyPoint wrist = new DoubleBodyPoint();
		
		DoubleBodyPoint knee = new DoubleBodyPoint();
		DoubleBodyPoint ankle = new DoubleBodyPoint();
		DoubleBodyPoint footPoint = new DoubleBodyPoint();
		
		ConnectionLine torso = new ConnectionLine(waistPoint,chest,150,0);
		ConnectionLine neck = new ConnectionLine(chest,throat,30,0);
		ConnectionLine head = new ConnectionLine(throat,forehead,20,0).paintType(Color.black, 50, true);
		
		DoubleBodyLine shoulder = new DoubleBodyLine(chest,shoulderPoint,20,-110);
		DoubleBodyLine armTop = new DoubleBodyLine(shoulderPoint,elbow,60,180);
		DoubleBodyLine armBottom = new DoubleBodyLine(elbow,wrist,50,180);
		
		DoubleBodyLine legTop = new DoubleBodyLine(waistPoint,knee,85,-150);
		DoubleBodyLine legBottom = new DoubleBodyLine(knee,ankle,70,180);
		DoubleBodyLine foot = new DoubleBodyLine(ankle,footPoint,25,-90);
		
		
		ComplexObject person = new ComplexObject(waistPoint,torso,shoulder.left,shoulder.right,armTop.left,armTop.right,armBottom.left,
				armBottom.right,legTop.left,legTop.right,legBottom.left,legBottom.right,foot.left,foot.right,neck,head);
		ObjectRegistry.addObject(person);
	}
	
	public static void createRope(){
		ConnectionBase base = new ConnectionBase(new Point2D.Double(40,200));
		ConnectionPoint p1 = new ConnectionPoint();
		ConnectionPoint p2 = new ConnectionPoint();
		ConnectionPoint p3 = new ConnectionPoint();
		ConnectionPoint p4 = new ConnectionPoint();
		ConnectionPoint p5 = new ConnectionPoint();
		
		ConnectionLine sb1 = new ConnectionLine(base,p1,50,180);
		ConnectionLine s12 = new ConnectionLine(p1,p2,50,180);
		ConnectionLine s23 = new ConnectionLine(p2,p3,50,180);
		ConnectionLine s34 = new ConnectionLine(p3,p4,50,180);
		ConnectionLine s45 = new ConnectionLine(p4,p5,50,180);
		ComplexObject rope = new ComplexObject(base,sb1,s12,s23,s34,s45);
		ObjectRegistry.addObject(rope);
	}
}
