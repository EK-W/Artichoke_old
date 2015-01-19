import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D.Double;

import javax.swing.JFrame;



public class Display extends JFrame{
	
	public static Dimension screen = new Dimension(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	public static Dimension relative;
	
	public static void main(String[] args){
		Display d = new Display();
	}
	
	public Display(){
		this.setBackground(new Color(204,204,204));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setSize(screen);
		this.setFocusable(true);
		device.setFullScreenWindow(this);
		this.setVisible(true);
		relative = new Dimension(screen.width/1280,screen.height/800);
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
	}
}
