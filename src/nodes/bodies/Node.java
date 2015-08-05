package nodes.bodies;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import nodes.util.AngleMath;


public class Node implements Selectable {
	
	private static final double RADIUS = 7;
	protected Point2D location;
	private static final Color[] colors = {
		new Color(255, 207, 51),
		new Color(154, 0, 196),
		new Color(255, 94, 94),
		new Color(158, 249, 255)
	};
	private float lineThickness = 1;
	public static final boolean SNAP_ANGLE = true;
	private Color lineColor = new Color(0, 0, 0);
	protected ArrayList<Node> childNodes = new ArrayList<Node>();
	protected Point2D parentLoc;
	protected double angle = 180;
	protected double length = 25;
	private Body body;
	
	
	public Node(Point2D location){
		this.location = location;
	}
	
	public Node(double angle, double length){
		this.angle = angle;
		this.length = length;
	}
	public Node lineProperties(float lineThickness, Color lineColor){
		this.lineThickness = lineThickness;
		this.lineColor = lineColor;
		return this;
	}
	protected Node connectParent(Node parent){
		if(parent.location == null) throw new NullPointerException("attempted to connect a child to a node with a null location");
		parent.childNodes.add(this);
		body = parent.body;
		parentLoc = parent.location;
		//This will be true if the Node(angle, length) constructor is used
		if(location == null){
			location = AngleMath.getLocation(parent.location, angle, length);
		}else{
			angle = AngleMath.getAngle(parent.location, location);
			length = parent.location.distance(location);
		}
		return this;
	}
	
//	protected Node connectChild(Node child){
//		if(location == null) throw new NullPointerException("attempted to connect a child to a node with a null location");
//		childNodes.add(child);
//		child.parentLoc = location;
//		//This will be true if the Node(angle, length) constructor is used
//		if(child.location == null){
//			child.location = AngleMath.getLocation(location, child.angle, child.length);
//		}else{
//			child.angle = AngleMath.getAngle(location, child.location);
//			child.length = location.distance(child.location);
//		}
//		return this;
//	}
	
	@Override
	public Shape getShape(){
		return new Ellipse2D.Double(location.getX() - RADIUS, location.getY() - RADIUS, RADIUS * 2, RADIUS * 2);
	}
	
	protected void paintNode(Graphics2D g) {
		g.setColor(getNodeColor());
		g.fill(getShape());
		for(Node i: childNodes){
			i.paintNode(g);
		}
	}
	protected void paintLine(Graphics2D g) {
		for(Node i: childNodes){
			g.setColor(i.lineColor);
			g.setStroke(new BasicStroke(i.lineThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g.draw(new Line2D.Double(location, i.location));
//			int[] xPoints = {(int) location.getX(), (int) i.location.getX(), (int) i.location.getX(), (int) location.getX()};
//			int[] yPoints = {(int) location.getY(), (int) location.getY(), (int) i.location.getY(), (int) i.location.getY()};
//			g.drawPolygon(xPoints, yPoints, 4);
			i.paintLine(g);
		}
	}
	
	@Override
	public void updateAsSelected(Point2D p) {
		body.updateNode(p, this);
	}
	private void snapAngle() {
//		int snapDistance = 10;
//		for(int i = 0; i < 359; i += 45){
//			if(i - snapDistance < angle && angle < i + snapDistance){
//				angle = i - 180;
//			}
//		}
	}

	

	@Override
	public Selectable checkSelected(Point2D p) {
		for(Node i: childNodes){
			Selectable temp = i.checkSelected(p);
			if(temp != null) return temp;
		}
		if(getShape().contains(p)){
			return this;
		}else{
			return null;
		}
	}
	private Color getNodeColor(){
		return colors[(parentLoc == null? 0 : 1) + (childNodes.isEmpty()? 0 : 2)];
	}

	@Override
	public String[] getDebugInfo() {
		return new String[] {
			"Selected: " + toString(),
			"Selected angle: " + angle,
			"Selected length: " + length,
			"Selected location: X: " + location.getX() + " Y: " + location.getY(),
			"Selected parentLoc: " + (parentLoc == null? "null" : "X: " + parentLoc.getX() + " Y: " + parentLoc.getY()),
		};
	}
	
	public Node clone(){
		Node ret = new Node(new Point2D.Double(location.getX(), location.getY()));
		ret.lineProperties(lineThickness, new Color(lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue(), lineColor.getAlpha()));
		for(Node i: childNodes){
			i.clone().connectParent(ret);
		}
		return ret;
	}

	public void paintMask(Graphics2D g) {
		for(Node i: childNodes){
			g.setColor(Color.black);
			g.setStroke(new BasicStroke(i.lineThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g.draw(new Line2D.Double(location, i.location));
			i.paintLine(g);
		}
	}
	
	@Override
	public Body getBody(){
		return body;
	}
	protected Node updateBody(Body body){
		this.body = body;
		for(Node i: childNodes){
			i.updateBody(body);
		}
		return this;
	}
}
