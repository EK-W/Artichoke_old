package node;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;

import running.InputHandler;
import util.AngleMath;

public class Node implements Selectable{
	
	private static final double RADIUS = 7;
	private Point2D location;
	private static final Color middleColor = new Color(158, 249, 255);
	private static final Color endColor = new Color(154, 0, 196);
	private static final Color baseColor = new Color(255, 94, 94);
	private static final Color loneColor = new Color(255, 207, 51);
	private float lineThickness = 1;
	public static final boolean SNAP_ANGLE = true;
	private Color lineColor = new Color(0, 0, 0);
	private HashSet<Node> childNodes = new HashSet<Node>();
	private Node parentNode;
	private double angle = 180;
	private double length = 25;
	private boolean lockedAngle = true;
	private boolean lockedLength = true;
	
	
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
	public Node connectChild(Node child){
		if(location == null) throw new NullPointerException("attempted to connect a child to a node with a null location");
		childNodes.add(child);
		child.parentNode = this;
		//This will be true if the Node(angle, length) constructor is used
		if(child.location == null){
			child.location = AngleMath.getLocation(location, child.angle, child.length);
		}else{
			child.angle = AngleMath.getAngle(location, child.location);
			child.length = location.distance(child.location);
		}
		return this;
	}
	
	public Node connectParent(Node parent){
		parent.connectChild(this);
		return this;
	}
	
	public Ellipse2D getEllipse(){
		return new Ellipse2D.Double(location.getX() - RADIUS, location.getY() - RADIUS, RADIUS * 2, RADIUS * 2);
	}
	
	public void paintNode(Graphics2D g) {
		g.setColor(getNodeColor());
		g.fill(getEllipse());
		for(Node i: childNodes){
			i.paintNode(g);
		}
	}
	public void paintLine(Graphics2D g) {
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
	
	public void updateAsSelected() {
		if(parentNode == null){
			//If a node has no parents, it is free. Same for humans...
			location.setLocation(InputHandler.mouseLoc);
			updateLine(0);
		}else{
			double delta = angle;
			angle = AngleMath.getAngle(parentNode.location, InputHandler.mouseLoc);
			if(!lockedLength){
				length = parentNode.location.distance(InputHandler.mouseLoc);
			}
			if(SNAP_ANGLE){
				snapAngle();
			}
			location = AngleMath.getLocation(parentNode.location, angle, length);	
			updateLine(delta - angle);
		}
	}
	private void snapAngle() {
//		int snapDistance = 10;
//		for(int i = 0; i < 359; i += 45){
//			if(i - snapDistance < angle && angle < i + snapDistance){
//				angle = i - 180;
//			}
//		}
	}

	private void updateLine(double delta){
		for(Node i: childNodes){
			//i.angle += (i.lockedAngle? delta : 0);
			i.angle -= (i.lockedAngle? delta : 0);
			i.location = AngleMath.getLocation(location, i.angle, i.length);	
		}
		for(Node i: childNodes){
			i.updateLine(delta);
		}
	}


	public boolean checkSelected() {
		for(Node i: childNodes){
			if(i.checkSelected()) return true;
		}
		if(getEllipse().contains(InputHandler.mouseLoc)){
			InputHandler.selected = this;
			return true;
		}else{
			return false;
		}
	}
	private Color getNodeColor(){
		if(childNodes.isEmpty()){
			if(parentNode == null){
				return loneColor;
			}else{
				return endColor;
			}
		}else{
			if(parentNode == null){
				return baseColor;
			}else{
				return middleColor;
			}
		}
	}

	@Override
	public String[] getDebugInfo() {
		return new String[] {
			"Selected angle: " + angle,
		};
	}

}
