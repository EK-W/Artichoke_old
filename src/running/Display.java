package running;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import bodies.Slide;
import running.console.Console;

public class Display extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4312883470921417750L;

	public Display(){
		
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0,(int) Main.screen.getWidth(),(int) Main.screen.getHeight());
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(Main.xOffset/2, Main.yOffset/2);
		g2.scale(Main.paintScale, Main.paintScale);
		
		g.setColor(Color.white);
		g.fillRect(0, 0, Main.baseRes.width, Main.baseRes.height);
		g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC));
		g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
		//g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
		paintItems(g2);
		debugItems(g2);
	}
	
	private void paintItems(Graphics2D g){
		Slide.paint(g);
	}
	
	private void debugItems(Graphics2D g){
		Console.paint(g);
	}
}
