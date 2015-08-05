package nodes.bodies;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public abstract class Body{
	abstract public void paint(Graphics2D g);
	abstract public void paintImg(Graphics2D g);
	public abstract void paintMask(Graphics2D g);
	abstract public Selectable checkSelected(Point2D p);
	public abstract Body clone();
	public Body(Node n) {
		n.updateBody(this);
	}
	public abstract void add(Node arg0, Node arg1);
	public abstract void add(Node arg0);
	protected abstract void updateNode(Point2D p, Node n);
}
