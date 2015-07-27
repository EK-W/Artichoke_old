package bodies;

import java.awt.geom.Point2D;

public class DoubleNode {
	Node left;
	Node right;
	
	public DoubleNode(Node parent, double angle, double length){
		left = new Node(parent, angle, length);
		right = new Node(parent, angle * -1, length);
	}
}
