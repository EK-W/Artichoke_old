package nodes.util;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import nodes.bodies.BoundTree;
import nodes.bodies.Node;
import nodes.bodies.UnboundPolygon;

public class BodyBuilder {
	public static BoundTree makePerson(Point2D location, double size){
		Node pelvis = new Node(location);
		Node torso = new Node(0, 50 * size).lineProperties((float) (3 * size), Color.black);
		Node leftThigh = new Node(-165, 32 * size).lineProperties((float) (3 * size), Color.black);
		Node rightThigh = new Node(165, 32 * size).lineProperties((float) (3 * size), Color.black);
		Node leftShin = new Node(-180, 32 * size).lineProperties((float) (3 * size), Color.black);
		Node rightShin = new Node(180, 32 * size).lineProperties((float) (3 * size), Color.black);
		Node leftFoot = new Node(-90, 10 * size).lineProperties((float) (3 * size), Color.black);
		Node rightFoot = new Node(90, 10 * size).lineProperties((float) (3 * size), Color.black);
		Node leftShoulder = new Node(-100, 13 * size).lineProperties((float) (3 * size), Color.black);
		Node rightShoulder = new Node(100, 13 * size).lineProperties((float) (3 * size), Color.black);
		Node leftBicep = new Node(-165, 24 * size).lineProperties((float) (3 * size), Color.black);
		Node rightBicep = new Node(165, 24 * size).lineProperties((float) (3 * size), Color.black);
		Node leftForearm = new Node(-180, 24 * size).lineProperties((float) (3 * size), Color.black);
		Node rightForearm = new Node(180, 24 * size).lineProperties((float) (3 * size), Color.black);
		Node neck = new Node(0, 11 * size).lineProperties((float) (3 * size), Color.black);
		Node head = new Node(0, 10 * size).lineProperties((float) (20 * size), Color.black);
		BoundTree ret = new BoundTree(pelvis);
		ret.add(pelvis, torso);
		ret.add(pelvis, leftThigh);
		ret.add(pelvis, rightThigh);
		ret.add(leftThigh, leftShin);
		ret.add(rightThigh, rightShin);
		ret.add(leftShin, leftFoot);
		ret.add(rightShin, rightFoot);
//		ret.add(torso, leftShoulder);
//		ret.add(torso, rightShoulder);
//		ret.add(leftShoulder, leftBicep);
//		ret.add(rightShoulder, rightBicep);
		ret.add(torso, leftBicep);
		ret.add(torso, rightBicep);
		ret.add(leftBicep, leftForearm);
		ret.add(rightBicep, rightForearm);
		ret.add(torso, neck);
		ret.add(neck, head);
		return ret;
	}
	public static UnboundPolygon makePolygon(Point2D location, double length, int amt){
		UnboundPolygon ret = new UnboundPolygon(new Node(location));
		for(int i = 0; i < amt; i++){
			ret.add(new Node((360d / amt) * i, length));
		}
		return ret;
	}
}
