package bodies;

import java.awt.Graphics2D;
import java.util.ArrayList;

import node.Node;

public interface Body {
	/* 
	 * Using an arraylist rather than any recursion COULD work (Though it might need to be shifted when a node gets a new 
	 * Connection further thought required.The idea is that each node adds its children and that childs children. That way, a
	 * Nodes children will always be after it. This approach would guarantee a list that is not ordered least to most depth.
	 */
	public abstract void paint(Graphics2D g);
	//ArrayList is the best structure for this... I think. Cuz things will be accessed often and rarely added/removed
	ArrayList<Node> nodes = new ArrayList<Node>();
	
}
