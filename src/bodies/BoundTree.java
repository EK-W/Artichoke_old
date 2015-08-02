package bodies;

import java.awt.Graphics2D;

import node.Node;

public class BoundTree extends Body implements Cloneable{
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
	public boolean checkSelected() {
		return base.checkSelected();
	}
	@Override
	public void paintImg(Graphics2D g) {
		base.paintLine(g);
	}
	@Override
	public Object clone() {
		return new BoundTree((Node) base.clone());
	}
	
}
