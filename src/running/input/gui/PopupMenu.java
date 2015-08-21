package running.input.gui;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class PopupMenu {
	GuiObject[] guiObjects;
	boolean open = false;
	
	public PopupMenu(GuiObject...guiObjects){
		this.guiObjects = guiObjects;
	}
	
	public void paint(Graphics2D g){
		if(open){
			for(GuiObject i: guiObjects){
				i.paint(g);
			}
		}
	}
	
	public void open(Point loc){
		open = true;
		int height = 0;
		for(GuiObject i: guiObjects){
			i.setLocation(new Point(loc.x, loc.y + height));
			height += i.bounds.height;
			i.setEnabled(true);
		}
		
	}
	public void close(){
		open = false;
		for(GuiObject i: guiObjects){
			i.setEnabled(false);
		}
	}
	
	public boolean checkClick(Point p){
		if(!open)
			return false;
		for(GuiObject i: guiObjects){
			if(i.contains(p)){
				if(i instanceof InputBar){
					((InputBar) i).setOpen();
				} else {
					i.execute();
				}
				return true;
			} else {
				if(i instanceof InputBar){
					((InputBar) i).setClosed();
				}
			}
		}
		return false;
	}

}
