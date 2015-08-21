package running.input.gui;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import running.input.events.GuiEvent;

public abstract class GuiObject {
	protected Rectangle bounds;
	protected boolean enabled = true;
	protected GuiEvent toExecute;
	
	public GuiObject(Rectangle bounds, GuiEvent toExecute){
		this.bounds = bounds;
		this.toExecute = toExecute;
	}
	
	public abstract void execute();
	public abstract void paint(Graphics2D g);
	
	public GuiObject setEnabled(boolean enabled){
		this.enabled = enabled;
		return this;
	}
	
	public void setLocation(Point p){
		bounds.setBounds(p.x, p.y, bounds.width, bounds.height);
	}
	
	public boolean contains(Point p){
		return enabled && bounds.contains(p);
	}
}
