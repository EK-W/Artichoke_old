package bodies;

import java.awt.Graphics2D;



public interface Body {
	abstract void add(Node n, Node parent);
	abstract void add(double angle, double length, Node parent);
	abstract void paint(Graphics2D g);
	abstract boolean checkSelected();
}
