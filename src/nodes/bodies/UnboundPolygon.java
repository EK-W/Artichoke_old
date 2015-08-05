package nodes.bodies;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import nodes.util.AngleMath;

public class UnboundPolygon extends Body implements Selectable{
	Point2D location;
	Color outline = Color.black;
	Color color = Color.red;
	float outlineThickness = 1;
	ArrayList<Node> nodes = new ArrayList<Node>();
	
	public UnboundPolygon(Node n) {
		super(n);
		location = n.location;
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(color);
		g.fill(getShape());
		g.setColor(outline);
		g.setStroke(new BasicStroke(outlineThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.draw(getShape());
		for(Node i: nodes){
			i.paintNode(g);
		}
	}

	public UnboundPolygon setView(Color color, Color outline, float outlineThickness){
		this.color = color;
		this.outline = outline;
		this.outlineThickness = outlineThickness;
		return this;
	}
	
	@Override
	public void paintImg(Graphics2D g) {
		g.setColor(color);
		g.fill(getShape());
		g.setColor(outline);
		g.setStroke(new BasicStroke(outlineThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.draw(getShape());
//		for(int i = 0; i < nodes.size(); i++){
//			g.draw(new Line2D.Double(nodes.get(i).location, nodes.get((i + 1) % nodes.size()).location));
//		}
	}

	@Override
	public Selectable checkSelected(Point2D p) {
		for(Node i: nodes){
			Selectable temp = i.checkSelected(p);
			if(temp != null)
				return temp;
		}
		if(getShape().contains(p)){
			location.setLocation(p);
			return this;
		}
		return null;
	}

	@Override
	public Body clone() {
		UnboundPolygon ret = new UnboundPolygon(new Node(new Point2D.Double(location.getX(), location.getY())));
		ret.outline = new Color(outline.getRGB());
		ret.outlineThickness = outlineThickness;
		for(Node i: nodes){
			ret.add(i.clone());
		}
		return ret;
	}

	@Override
	public void paintMask(Graphics2D g) {
		for(Node i: nodes){
			i.paintMask(g);
		}
	}

	@Override
	public void add(Node arg0, Node arg1) {
		if(arg1.location == null){
			arg1.location = AngleMath.getLocation(location, arg1.angle, arg1.length);
		}
		nodes.add(nodes.indexOf(arg0), arg1);
		arg1.updateBody(this);
	}

	@Override
	protected void updateNode(Point2D p, Node n) {
		n.location.setLocation(p);
	}

	@Override
	public void add(Node arg0) {
		if(arg0.location == null){
			arg0.location = AngleMath.getLocation(location, arg0.angle, arg0.length);
		}
		nodes.add(arg0);
		arg0.updateBody(this);
	}

	@Override
	public void updateAsSelected(Point2D p) {
		double dX = p.getX() - location.getX();
		double dY = p.getY() - location.getY();
		location.setLocation(p);
		for(Node i: nodes){
			i.location.setLocation(i.location.getX() + dX, i.location.getY() + dY);
		}
	}

	@Override
	public String[] getDebugInfo() {
		return new String[] {
				"Selected: " + toString(),
				"Selected location: X: " + location.getX() + " Y: " + location.getY()
			};
	}

	@Override
	public Shape getShape() {
		int[] xLocs = new int[nodes.size() + 1];
		int[] yLocs = new int[nodes.size() + 1];
		for(int i = 0; i <= nodes.size(); i++){
			xLocs[i] = (int) nodes.get(i % nodes.size()).location.getX();
			yLocs[i] = (int) nodes.get(i % nodes.size()).location.getY();
		}
		return new Polygon(xLocs, yLocs, nodes.size());
	}

	@Override
	public Body getBody() {
		return this;
	}
	
}
