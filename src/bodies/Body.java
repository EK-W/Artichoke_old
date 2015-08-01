package bodies;

import java.awt.Graphics2D;

import node.Node;

public abstract class Body {
	abstract public void paint(Graphics2D g);
	abstract public void paintImg(Graphics2D g);
	abstract public boolean checkSelected();
	protected Body(Node n) {
		
	}
}
