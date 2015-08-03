package nodes.bodies;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class BoundTree extends Body implements Cloneable {
	Node base;
	public BoundTree(Node n) {
		super(n);
		base = n;
	}
	@Override
	public void paint(Graphics2D g) {
		base.paintLine(g);
		base.paintNode(g);
	}
	@Override
	public Selectable checkSelected(Point2D p) {
		return base.checkSelected(p);
	}
	@Override
	public void paintImg(Graphics2D g) {
		base.paintLine(g);
	}
	@Override
	public Object clone() {
		return new BoundTree((Node) base.clone());
	}
	@Override
	public void paintMask(Graphics2D g) {
		base.paintMask(g);
	}
	
}
