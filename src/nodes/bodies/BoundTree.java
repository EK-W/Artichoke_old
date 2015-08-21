package nodes.bodies;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import nodes.util.AngleMath;

public class BoundTree extends Body {
	public BoundTree(Node base) {
		super(base);
		this.base = base;
	}

	Node base;

	@Override
	public void paint(Graphics2D g) {
		base.paintLine(g);
		base.paintNode(g);
	}

	@Override
	public void paintImg(Graphics2D g) {
		base.paintLine(g);
	}

	@Override
	public void paintMask(Graphics2D g) {
		base.paintMask(g);
	}

	@Override
	public Selectable checkSelected(Point2D p) {
		return base.checkSelected(p);
	}

	@Override
	public Body clone() {
		return new BoundTree(base.clone());
	}

	@Override
	public void add(Node parent, Node node) {
		if(!base.contains(parent)) throw new IllegalArgumentException();
		node.connectParent(parent);
	}

	@Override
	protected void updateNode(Point2D p, Node n) {
		if(n.parentLoc == null){
			//If a node has no parents, it is free. Same for humans...
			n.location.setLocation(p);
			updateLine(0, n);
		}else{
			double delta = n.angle;
			n.angle = AngleMath.getAngle(n.parentLoc, p);
			n.update();
			//location = (AngleMath.getLocation(parentLoc, angle, length));
			updateLine(delta - n.angle, n);
		}
	}
	private void updateLine(double delta, Node n){
		for(Node i: n.childNodes){
			//i.angle += (i.lockedAngle? delta : 0);
			i.angle -= delta;
			i.update();
		}
		for(Node i: n.childNodes){
			updateLine(delta, i);
		}
	}
	@Override
	public void add(Node arg0) {
		arg0.connectParent(base);
	}
	
}
