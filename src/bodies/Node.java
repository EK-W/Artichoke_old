package bodies;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import running.InputHandler;
import util.AngledLine;

public class Node implements Selectable{
	
	public static final double RADIUS = 7;
	private Point2D location = new Point2D.Double(100, 100);
	private static final Color middleColor = new Color(158, 249, 255);
	private static final Color endColor = new Color(154, 0, 196);
	private static final Color baseColor = new Color(255, 94, 94);
	private static final Color loneColor = new Color(255, 207, 51);
	private float lineThickness = 1;
	private Color lineColor = new Color(0, 0, 0);
	private Connection parentConnection = null;
	private Connection childConnection = null;
	private AngledLine parentLine = null;

	
	public Node(Point2D location){
		this.location = location;
	}
	protected Node(Point2D location, Node parent){
		this.location = location;
		parent.connect(this);
		parentLine = new AngledLine(parent.location,location);
	}
	protected Node(Node parent, double angle, double length){
		parent.connect(this);
		parentLine = new AngledLine(parent.location, angle, length);
		location = parentLine.getEnd();
	}
	public Node lineProperties(float lineThickness, Color lineColor){
		this.lineThickness = lineThickness;
		this.lineColor = lineColor;
		return this;
	}
	public Connection getParent(){
		return parentConnection;
	}
	public Connection getChild(){
		return childConnection;
	}
	protected void connect(Node to){
		if(to.parentConnection != null){
			to.parentConnection.remove(to);
		}
		if(this.childConnection == null){
			this.childConnection = new Connection();
		}
		childConnection.add(to);
		to.parentConnection = childConnection;
	}
	public Ellipse2D getEllipse(){
		return new Ellipse2D.Double(location.getX() - RADIUS, location.getY() - RADIUS, RADIUS * 2, RADIUS * 2);
	}
	public void paintNode(Graphics2D g) {
		g.setColor(getNodeColor());
		g.fill(getEllipse());
	}
	public void paintLine(Graphics2D g) {
		if(parentLine != null){
			g.setColor(lineColor);
			g.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g.draw(parentLine.shape());
		}
	}
	
	public void updateAsSelected() {
		if(parentConnection == null){
			//If a node has no parents, it is free. Same for humans...
			location.setLocation(InputHandler.mouseLoc);
		}else{
			parentLine.setAngle(parentLine.getAngleTo(InputHandler.mouseLoc));
		}
		updateLine();
	}
	protected void updateLine(){
		if(parentLine != null){
			parentLine.update();
		}
		if(childConnection != null){
			childConnection.updateChildLines();
		}
	}

	@Override
	public boolean checkSelected() {
		if(childConnection == null || !childConnection.checkChildrenSelected()){
			if(getEllipse().contains(InputHandler.mouseLoc)){
				InputHandler.selected = this;
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	private Color getNodeColor(){
		if(childConnection == null){
			if(parentConnection == null){
				return loneColor;
			}else{
				return endColor;
			}
		}else{
			if(parentConnection == null){
				return baseColor;
			}else{
				return middleColor;
			}
		}
	}
	

}
