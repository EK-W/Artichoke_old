package nodes.bodies;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public abstract class Body implements Cloneable{
	abstract public void paint(Graphics2D g);
	abstract public void paintImg(Graphics2D g);
	abstract public Selectable checkSelected(Point2D p);
	@Override
	public abstract Object clone();
	protected Body(Node n) {
		
	}
	public abstract void paintMask(Graphics2D g);
}
