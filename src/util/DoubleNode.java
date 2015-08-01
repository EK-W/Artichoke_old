package util;

import java.awt.Color;

import node.Node;

public class DoubleNode {
	public Node left;
	public Node right;
	public DoubleNode(double angle, double length){
		left = new Node(angle, length);
		right = new Node(-angle, length);
	}
	public DoubleNode lineProperties(float thickness, Color col){
		left.lineProperties(thickness, col);
		right.lineProperties(thickness, col);
		return this;
	}
	public DoubleNode connectParent(Node parent){
		left.connectParent(parent);
		right.connectParent(parent);
		return this;
	}
	public DoubleNode connectParent(DoubleNode parent){
		left.connectParent(parent.left);
		right.connectParent(parent.right);
		return this;
	}
}
