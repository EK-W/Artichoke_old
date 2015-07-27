package bodies;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BoundTree implements Body{
	ArrayList<Node> nodes = new ArrayList<Node>();
	Node base;
	public BoundTree(Node base){
		nodes.add(base);
		this.base = base;
	}
	@Override
	public void add(Node node, Node parent){
		if(!nodes.contains(parent)) throw new IllegalArgumentException("Parent node not added to body");
		if(nodes.contains(node)) throw new IllegalArgumentException("Node already exists in the body");
		parent.connect(node);
		nodes.add(node);
	}
	@Override
	public void add(double angle, double length, Node parent){
		if(!nodes.contains(parent)) throw new IllegalArgumentException("Parent node not added to body");
		Node n = new Node(parent, angle, length);
		parent.connect(n);
		nodes.add(n);
	}
	@Override
	public void paint(Graphics2D g){
		for(Node i: nodes){
			i.paintLine(g);
		}
		for(Node i: nodes){
			i.paintNode(g);
		}
	}
	@Override
	public boolean checkSelected() {
		return base.checkSelected();
	}
}
