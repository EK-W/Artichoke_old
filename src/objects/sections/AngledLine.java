package objects.sections;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


public class AngledLine{
	
	
	public AngledLine(Point2D location, double angle, double length){
		this.length=length;
		base = location;
		end = new Point2D.Double();
		this.setAngle(angle);
		
	}
	
	Point2D base;
	Point2D end;
	double deltaAngle;
	
	//Angle methods
	private double angle;
	public double getAngle(){
		return angle;
	}
	
	public void update(double da){
		changeAngle(da);
		//end.setLocation(base.getX()+(Math.cos(Math.toRadians(angle-90))*length),base.getY()+(Math.sin(Math.toRadians(angle-90))*length));
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
	
	public void pointAt(Point2D at){
		double previousAngle = angle;
		setAngle(Math.toDegrees(Math.atan2(at.getY()-base.getY(), at.getX()-base.getX()))+90);
		deltaAngle = angle-previousAngle;
	}
	
	
	public Line2D shape(){
		return new Line2D.Double(base,end);
	}
	
}
