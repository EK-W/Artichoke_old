package objects.sections;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class ConnectionLine {
	public AngledLine line;
	private BodyPoint parent;
	public ConnectionPoint child;
	private BasicStroke stroke;
	private Color color;
	public ConnectionLine(BodyPoint b,float length,double angle){
		parent = b;
		if(parent.children==null)parent.children=new ArrayList<ConnectionLine>();
		parent.children.add(this);
		line = new AngledLine(parent.location, angle, length);
		color = Color.black;
		stroke = new BasicStroke(4);
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
		stroke = new BasicStroke(4);
	}
	public ConnectionLine paintType(Color c, float t, boolean r){
		if(r){
			stroke = new BasicStroke(t,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		}else{
			stroke = new BasicStroke(t);
		}
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
		g.setStroke(stroke);
		g.draw(line.shape());
	}
	
}
