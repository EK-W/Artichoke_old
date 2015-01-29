package objects.sections;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import running.Display;
import running.MouseHandler;


public class ConnectionPoint extends BodyPoint{	
	private boolean isSelectable;
	public ConnectionPoint(){
		super();
		isSelectable=true;
	}
	public ConnectionPoint setUnselectable(boolean unselectable){
		isSelectable=!unselectable;
		return this;
	}
//	public boolean selected = false;
	
	@Override
	public void showPoint(Graphics2D g){
		if(isSelectable()){
			g.setColor(new Color(69,94,255,Display.showSelectables));
		}else{
			g.setColor(new Color(255,89,0,Display.showSelectables));
		}
		g.fill(new Ellipse2D.Double(location.getX()-radius, location.getY()-radius, radius*2, radius*2));
		g.setColor(new Color(204,229,255,Display.showSelectables));
		g.fill(new Ellipse2D.Double(location.getX()-(radius-outlineSize), location.getY()-(radius-outlineSize), (radius-outlineSize)*2, (radius-outlineSize)*2));
	}
	
	public void updateAsSelected(){
			parentLine.line.pointAt(MouseHandler.mouseLoc);
			if(children!=null){
				for(int i=0;i<children.size();i++){
					children.get(i).update(parentLine.line.deltaAngle);
				}
			}
	}
//	public void update(double da){
//		parentLine.update(da);
//	}
	public boolean isSelectable() {
		return isSelectable;
	}
	
	
}
