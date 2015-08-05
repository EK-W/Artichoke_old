package running.displayPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import running.Main;
import running.input.console.Console;

public abstract class DisplayPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5298877747564206746L;
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0,(int) Main.screen.getWidth(),(int) Main.screen.getHeight());
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(Main.xOffset/2, Main.yOffset/2);
		g2.scale(Main.paintScale, Main.paintScale);
		g.setFont(Main.defaultFont);
		g.setColor(Color.white);
		g.fillRect(0, 0, Main.baseRes.width, Main.baseRes.height);
		g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC));
		g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
		paint(g2);
		Console.paint(g2);
	}
	
	protected abstract void paint(Graphics2D g);
	
	public abstract String[] getDebugInfo();
	public abstract void onMousePress(Point2D p, int button);
	public abstract void onMouseRelease(Point2D p, int button);
	public abstract void onMouseMove(Point2D p, boolean pressed);
	public abstract void onKeyPress(KeyEvent e);
}
