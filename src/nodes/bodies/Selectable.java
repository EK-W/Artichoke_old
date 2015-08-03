package nodes.bodies;

import java.awt.geom.Point2D;

public interface Selectable{
	public void updateAsSelected(Point2D p);
	public Selectable checkSelected(Point2D p);
	public String[] getDebugInfo();
}
