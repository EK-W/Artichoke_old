package nodes.util;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

//Made by Harry Brickner at some point before March 11 2015.

public class AngleMath{
	
	public static Line2D getLine(Point2D from, double angle, double length){
		return new Line2D.Double(from, getLocation(from, angle, length));
	}
	
	public static  Point2D getLocation(Point2D from, double angle, double length){
		return new Point2D.Double(from.getX() + (Math.cos(Math.toRadians(angle - 90)) * length), from.getY() + (Math.sin(Math.toRadians(angle - 90))*length));
	}
	public static double findBoundAngle(double degrees){
		//return ((((degrees + 180) % 360) + 360) % 360) - 180;
		return ((degrees % 360) + 360) % 360;
	}
	
	public static double getAngle(Point2D from, Point2D to){
		return findBoundAngle(Math.toDegrees(Math.atan2(to.getY() - from.getY(), to.getX() - from.getX())) + 90);
	}
}