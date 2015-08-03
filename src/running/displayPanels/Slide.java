package running.displayPanels;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import nodes.bodies.Body;
import nodes.bodies.Selectable;

public class Slide{
	//ArrayList because it wont be added to often but will be referenced multiple times
	protected ArrayList<Body> bodies;
	
	
	protected void addBody(Body b){
		bodies.add(b);
	}
	
	protected Slide(){
		bodies = new ArrayList<Body>();
	}
	
	protected Slide(ArrayList<Body> toAdd){
		this.bodies = new ArrayList<Body>(toAdd.size());
		for(Body i: toAdd){
			this.bodies.add((Body) i.clone());
		}
	}
	
	public Selectable checkSelected(Point2D p){
		for(Body i: bodies){
			Selectable temp = i.checkSelected(p);
			if(temp != null) return temp;
		}
		return null;
	}
	
	public void paint(Graphics2D g){
		for(Body i: bodies){
			i.paint(g);
		}
	}
	
	public void paintImg(Graphics2D g){
		for(Body i: bodies){
			i.paintImg(g);
		}
	}

	public void paintMask(Graphics2D g) {
		for(Body i: bodies){
			i.paintMask(g);
		}
	}
}
