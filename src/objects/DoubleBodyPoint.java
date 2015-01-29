package objects;

import objects.sections.ConnectionPoint;

public class DoubleBodyPoint {
	public ConnectionPoint left;
	public ConnectionPoint right;
	
	public DoubleBodyPoint(){
		left = new ConnectionPoint();
		right = new ConnectionPoint();
	}
	public DoubleBodyPoint setUnselectable(){
		left.setUnselectable(true);
		right.setUnselectable(true);
		return this;
	}
}
