package objects.sections;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

import running.MouseHandler;

public class ConnectionBase extends BodyPoint implements Serializable{

	public ConnectionBase(Point2D loc){
		super();
		location = loc;
	}
	
	@Override
	public void showPoint(Graphics2D g){
		g.setColor(new Color(69,94,255));
		g.fill(new Ellipse2D.Double(location.getX()-radius, location.getY()-radius, radius*2, radius*2));
		g.setColor(new Color(204,0,44));
		g.fill(new Ellipse2D.Double(location.getX()-(radius-outlineSize), location.getY()-(radius-outlineSize), (radius-outlineSize)*2, (radius-outlineSize)*2));
	}

	@Override
	public void updateAsSelected(){
		location.setLocation(MouseHandler.mouseLoc);
		if(children!=null){
			for(int i=0;i<children.size();i++){
				children.get(i).update(0);
			}
		}	
	}
}
