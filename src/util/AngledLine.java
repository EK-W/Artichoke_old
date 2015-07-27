package util;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

//Made by Harry Brickner at some point before March 11 2015.

public class AngledLine implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2373136852044526957L;
	
	public AngledLine(Point2D base, Point2D end){
		this.base = base;
		this.end = end;
		this.length = base.distance(end);
		this.setAngle(getAngleTo(end));
	}
	public AngledLine(Point2D location, double angle, double length){
		this.length = length;
		base = location;
		end = new Point2D.Double();
		this.setAngle(angle);
	}
	
	private Point2D base;
	private Point2D end;
	
	public Point2D getBase(){
		return base;
	}
	public Point2D getEnd(){
		return end;
	}
	
	public void setBase(Point2D to){
		base = to;
		update();
	}
//	public void setEnd(Point2D to){
//		end = to;
//		update();
//	}
	
	//Angle methods
	private double angle;
	public double getAngle(){
		return angle;
	}
	
	public void update(){
		end.setLocation(base.getX()+(Math.cos(Math.toRadians(angle-90))*length),base.getY()+(Math.sin(Math.toRadians(angle-90))*length));
	}
	public void setAngle(double degrees){
		angle = degrees;
		while(angle>180)angle-=360;
		while(angle<-180)angle+=360;
		end.setLocation(base.getX()+(Math.cos(Math.toRadians(angle-90))*length),base.getY()+(Math.sin(Math.toRadians(angle-90))*length));
	}
	public void changeAngle(double degrees){
		angle += degrees;
		while(angle>180)angle-=360;
		while(angle<-180)angle+=360;
		end.setLocation(base.getX()+(Math.cos(Math.toRadians(angle-90))*length),base.getY()+(Math.sin(Math.toRadians(angle-90))*length));
	}
	
	//Length methods
	private double length;
	public double getLength(){
		return length;
	}
	
	public void setLength(double set){
		length=set;
		end.setLocation(base.getX()+(Math.cos(Math.toRadians(angle-90))*length),base.getY()+(Math.sin(Math.toRadians(angle-90))*length));
	}
	
	public void changeLength(double set){
		length+=set;
		end.setLocation(base.getX()+(Math.cos(Math.toRadians(angle-90))*length),base.getY()+(Math.sin(Math.toRadians(angle-90))*length));
	}
	
	public double getAngleTo(Point2D at){
		return Math.toDegrees(Math.atan2(at.getY()-base.getY(), at.getX()-base.getX()))+90;
	}
	
	public Line2D shape(){
		return new Line2D.Double(base,end);
	}
	
}