package objectSectionThings;
import java.awt.Point;
import java.awt.geom.Line2D;


public class AngledLine{
	
	public AngledLine(double x, double y, double angle, double length){
		this.length=length;
		x1=640;
		y1=400;
		this.setAngle(-45);
	}
	public AngledLine(Point location, double angle, double length){
		this(location.x,location.y,angle,length);
	}
	
	//X1 methods
	private double x1; 
	public double getX1(){
		return x1;
	}
	public void setX1(double set){
		x1=set;
		x2=x1+(Math.cos(Math.toRadians(angle-90))*length);
	}
	public void changeX1(double change){
		x1+=change;
		x2=x1+(Math.cos(Math.toRadians(angle-90))*length);
	}
	
	//Y1 methods
	private double y1;
	public double getY1(){
		return y1;
	}
	public void setY1(double set){
		y1=set;
		y2=y1+(Math.sin(Math.toRadians(angle-90))*length);
	}
	public void changeY1(double change){
		y1+=change;
		y2=y1+(Math.sin(Math.toRadians(angle-90))*length);
	}
	
	//X2 methods
	private double x2;
	public double getX2(){
		return x2;
	}
	
	//Y2 methods
	private double y2;
	public double getY2(){
		return y2;
	}
	
	//Angle methods
	private double angle;
	public double getAngle(){
		return angle;
	}
	public void setAngle(double degrees){
		angle = degrees;
		while(angle>180)angle-=360;
		while(angle<-180)angle+=360;
		x2=x1+(Math.cos(Math.toRadians(angle-90))*length);
		y2=y1+(Math.sin(Math.toRadians(angle-90))*length);
	}
	public void changeAngle(double degrees){
		angle += degrees;
		while(angle>180)angle-=360;
		while(angle<-180)angle+=360;
		x2=x1+(Math.cos(Math.toRadians(angle-90))*length);
		y2=y1+(Math.sin(Math.toRadians(angle-90))*length);
	}
	
	//Length methods
	private double length;
	public double getLength(){
		return length;
	}
	public void setLength(double set){
		length=set;
		x2=x1+(Math.cos(Math.toRadians(angle-90))*length);
		y2=y1+(Math.sin(Math.toRadians(angle-90))*length);
	}
	public void changeLength(double set){
		length+=set;
		x2=x1+(Math.cos(Math.toRadians(angle-90))*length);
		y2=y1+(Math.sin(Math.toRadians(angle-90))*length);
	}
	
	public void pointAt(int x, int y){
		setAngle(Math.toDegrees(Math.atan2(y-y1, x-x1))+90);
	}
	
	
	public Line2D shape(){
		return new Line2D.Double(x1,y1,x2,y2);
	}
	
}
