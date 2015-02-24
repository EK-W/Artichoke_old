package objects.sections;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import display.DisplaySettings;
import running.MouseHandler;


public class ConnectionPoint extends BodyPoint implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7829137664621105475L;
	private boolean isSelectable;
	public ConnectionPoint(){
		super();
		isSelectable=true;
	}
	
	@Override
	public ConnectionPoint clone(){
		ConnectionPoint ret = (ConnectionPoint) super.clone();
		ret.isSelectable = isSelectable;
		return ret;
	}
	public ConnectionPoint setUnselectable(boolean unselectable){
		isSelectable=!unselectable;
		return this;
	}
//	public boolean selected = false;
	
	@Override
	public void showPoint(Graphics2D g){
		if(isSelectable()){
			g.setColor(new Color(69,94,255,DisplaySettings.showSelectables));
		}else{
			g.setColor(new Color(255,89,0,DisplaySettings.showSelectables));
		}
		g.fill(new Ellipse2D.Double(location.getX()-radius, location.getY()-radius, radius*2, radius*2));
		g.setColor(new Color(204,229,255,DisplaySettings.showSelectables));
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
