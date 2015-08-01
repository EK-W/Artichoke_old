package util;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import node.Node;
import bodies.BoundTree;

public class BodyBuilder {
	public static BoundTree makePerson(Point2D location, double size){
		Node pelvis = new Node(location);
		Node torso = new Node(0, 200 * size).lineProperties((float) (12 * size), Color.black).connectParent(pelvis);
		DoubleNode thighs = new DoubleNode(165, 125 * size).lineProperties((float) (12 * size), Color.black).connectParent(pelvis);
		DoubleNode shins = new DoubleNode(180, 125 * size).lineProperties((float) (12 * size), Color.black).connectParent(thighs);
		DoubleNode feet = new DoubleNode(90, 40 * size).lineProperties((float) (10 * size), Color.black).connectParent(shins);
		DoubleNode shoulders = new DoubleNode(100, 50 * size).lineProperties((float) (12 * size), Color.black).connectParent(torso);
		DoubleNode bicep = new DoubleNode(165, 90 * size).lineProperties((float) (12 * size), Color.black).connectParent(shoulders);
		DoubleNode forearm = new DoubleNode(180, 90 * size).lineProperties((float) (12 * size), Color.black).connectParent(bicep);
		Node neck = new Node(0, 46 * size).lineProperties((float) (10 * size), Color.black).connectParent(torso);
		Node head = new Node(0, 40 * size).lineProperties((float) (80 * size), Color.black).connectParent(neck);
		return new BoundTree(pelvis);
	}
}
