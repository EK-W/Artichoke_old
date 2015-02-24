package objects.sections;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;


public class ConnectionLine implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8262450872036022908L;
	public AngledLine line;
	private BodyPoint parent;
	public ConnectionPoint child;
	private float thickness;
	private boolean round;
	private Color color;
	public ConnectionLine(BodyPoint b,float length,double angle){
		parent = b;
		if(parent.children==null)parent.children=new ArrayList<ConnectionLine>();
		parent.children.add(this);
		line = new AngledLine(parent.location, angle, length);
		color = Color.black;
		thickness = 4;
	}
	public ConnectionLine(BodyPoint p, ConnectionPoint c,float length,double angle){
		parent = p;
		if(parent.children==null)parent.children=new ArrayList<ConnectionLine>();
		parent.children.add(this);
		line = new AngledLine(parent.location, angle, length);
		child=c;
		child.location=line.end;
		child.parentLine=this;
		
		color = Color.black;
		thickness = 4;
		round = true;
	}
	public ConnectionLine paintType(Color c, float t, boolean r){
		thickness = t;
		round = r;
		color = c;
		
		return this;
	}
	public void update(double da){
		line.update(da);
		if(child.children!=null){
			for(int i=0;i<child.children.size();i++){
				child.children.get(i).update(da);
			}
			
		}
	}
	public ConnectionLine setChild(ConnectionPoint b){
		child=b;
		child.location=line.end;
		child.parentLine=this;
		return this;
	}
	public void paintLine(Graphics2D g){
		g.setColor(color);
		if(round)
			g.setStroke(new BasicStroke(thickness,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		else
			g.setStroke(new BasicStroke(thickness));
		g.draw(line.shape());
	}
	
	public ConnectionLine clone(){
		Color c = new Color(color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha());
		ConnectionLine ret = new ConnectionLine(parent.clone(),child,(float)line.getLength(),line.getAngle()).paintType(c, thickness, round);
		return ret;
	}
	
}
