package objectSectionThings;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


public class ObjectSection {
	public Color color;
	public float thickness;
	public AngledLine line;
	private Connection[] childConnections;
	
	public ObjectSection(AngledLine l, Color c, float t){
		line = l;
		color = c;
		thickness = t;
	}
	public void setChildConnections(Connection... connections){
		childConnections = connections;
	}
	
	public void paintSection(Graphics2D g){
		g.setColor(color);
		g.setStroke(new BasicStroke(thickness,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g.draw(line.shape());
	}
}
