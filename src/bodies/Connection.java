package bodies;

import java.awt.Graphics2D;
import java.util.HashSet;

public class Connection {
	private HashSet<Node> childNodes = new HashSet<Node>();
	//private Node parentNode;
	
	protected void add(Node n){
		childNodes.add(n);
	}
	public void remove(Node n){
		childNodes.remove(n);
	}
//	protected Node getParent(){
//		return parentNode;
//	}
//	protected HashSet<Node> getChildren(){
//		return childNodes;
//	}
	public boolean checkChildrenSelected() {
		for(Node i: childNodes){
			if(i.checkSelected()) return true;
		}
		return false;
	}
	//Exists solely for debugging
	public int length(){
		return childNodes.size();
	}
	public void updateChildLines() {
		for(Node i: childNodes){
			i.updateLine();
		}
		
	}
}
