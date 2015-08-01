package util;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import node.Node;
import bodies.BoundTree;

public class BodyBuilder {
	public static BoundTree makePerson(Point2D location, double size){
		Node pelvis = new Node(location);
		Node torso = new Node(0, 50 * size).lineProperties((float) (3 * size), Color.black).connectParent(pelvis);
		DoubleNode thighs = new DoubleNode(165, 32 * size).lineProperties((float) (3 * size), Color.black).connectParent(pelvis);
		DoubleNode shins = new DoubleNode(180, 32 * size).lineProperties((float) (3 * size), Color.black).connectParent(thighs);
		DoubleNode feet = new DoubleNode(90, 10 * size).lineProperties((float) (3 * size), Color.black).connectParent(shins);
		DoubleNode shoulders = new DoubleNode(100, 13 * size).lineProperties((float) (3 * size), Color.black).connectParent(torso);
		DoubleNode bicep = new DoubleNode(165, 24 * size).lineProperties((float) (3 * size), Color.black).connectParent(shoulders);
		DoubleNode forearm = new DoubleNode(180, 24 * size).lineProperties((float) (3 * size), Color.black).connectParent(bicep);
		Node neck = new Node(0, 11 * size).lineProperties((float) (3 * size), Color.black).connectParent(torso);
		Node head = new Node(0, 10 * size).lineProperties((float) (20 * size), Color.black).connectParent(neck);
		return new BoundTree(pelvis);
	}
}
