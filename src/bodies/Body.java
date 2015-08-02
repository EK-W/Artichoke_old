package bodies;

import java.awt.Graphics2D;

import node.Node;

public abstract class Body implements Cloneable{
	abstract public void paint(Graphics2D g);
	abstract public void paintImg(Graphics2D g);
	abstract public boolean checkSelected();
	@Override
	public abstract Object clone();
	protected Body(Node n) {
		
	}
}
