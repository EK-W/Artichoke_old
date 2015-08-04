package running.input;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import running.Main;

public class Button {
	Rectangle2D bounds;
	String name;
	ButtonEvent onClick;
	Color color = Color.white;
	Color border = Color.black;
	Color clickColor = new Color(0.75f, 0.75f, 0.75f);
	boolean clicked = false;
	Font font = Main.defaultFont;
	boolean enabled;
	
	public Button(String name, Rectangle2D bounds, boolean enabled, ButtonEvent onClick){
		this.name = name;
		this.bounds = bounds;
		this.enabled = enabled;
		this.onClick = onClick;
	}
	
	public Button setColors(Color color, Color border, Color clickColor){
		this.color = color;
		this.border = border;
		this.clickColor = clickColor;
		return this;
	}
	
	public Button setFont(Font font){
		this.font = font;
		return this;
	}
	
	public Button setEnabled(boolean enabled){
		this.enabled = enabled;
		return this;
	}
	
	public boolean mouseDown(Point2D p){
		if(enabled && bounds.contains(p)){
			clicked = true;
			onClick.execute();
			return true;
		} else {
			return false;
		}
	}
	public void mouseUp(){
		clicked = false;
	}
	
	public void paint(Graphics2D g){
		if(!enabled)
			return;
		Font temp = g.getFont();
		g.setFont(font);
		g.setColor(clicked? clickColor : color);
		g.fill(bounds);
		g.setColor(border);
		g.setStroke(new BasicStroke(1));
		g.draw(bounds);
		g.setColor(Color.black);
		int xLoc = (int) (bounds.getX() + (bounds.getWidth() / 2)  ) - (g.getFontMetrics().stringWidth(name) / 2);
		g.drawString(name, xLoc, (int) (bounds.getY() + (bounds.getHeight() / 2) + (g.getFontMetrics().getAscent() / 2)));
		g.setFont(temp);
	}
}
