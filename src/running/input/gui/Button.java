package running.input.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import running.input.events.GuiEvent;

public class Button extends GuiObject{
	BufferedImage img = null;
	String title;
	
//	public Button(Rectangle bounds, File f, GuiEvent onClick){
//		super(bounds, onClick);
//		try {
//			img = ImageIO.read(f);
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.exit(0);
//		}
//	}
//	
//	
//	public Button(Rectangle bounds, BufferedImage img, GuiEvent onClick){
//		super(bounds, onClick);
//		this.img = img;
//	}
	
	public Button(String title, Rectangle bounds, GuiEvent onClick){
		super(bounds, onClick);
		this.title = title;
	}


	@Override
	public void paint(Graphics2D g){
		if(!enabled)
			return;
		if(img == null){
			g.setColor(new Color(204, 252, 255));
			g.fill(bounds);
			g.setColor(new Color(102, 102, 102));
			g.draw(bounds);
			g.setColor(Color.black);
			g.drawString(title, bounds.x + (bounds.width / 2) - (g.getFontMetrics().stringWidth(title) / 2), 
					bounds.y + (bounds.height / 2) + (g.getFontMetrics().getAscent() / 2));
		} else {
			g.drawImage(img, bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height, 
					0, 0, img.getWidth(), img.getHeight(), null);
		}
	}

	@Override
	public void execute() {
		if(!enabled)
			return;
		toExecute.execute("");
	}
}
