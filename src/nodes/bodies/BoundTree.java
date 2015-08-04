package nodes.bodies;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class BoundTree extends Body {
	public BoundTree(Node base) {
		super(base);
		this.base = base;
		base.updateBody(this);
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
	protected boolean lockedAngle() {
		return true;
	}

	@Override
	protected boolean lockedLength() {
		return true;
	}
	
	
}
