package running;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.Timer;

import objects.ComplexObject;
import objects.ObjectCreator;
import objects.ObjectRegistry;
import objects.sections.ConnectionBase;
import objects.sections.ConnectionLine;
import objects.sections.ConnectionPoint;



public class Display extends JFrame implements ActionListener{
	
	public static Dimension screen = new Dimension(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
	public static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	public static Dimension relative;
	public Timer animate = new Timer(50,this);
	public static MouseHandler mouseHandler = new MouseHandler();
	public static KeyHandler keyHandler = new KeyHandler();
	public static int showSelectables=150;
	public static boolean screenshot = false;
	public static int currentSlide = 1;
	
	public Display(){
		this.setBackground(new Color(204,204,204));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setSize(screen);
		this.setFocusable(true);
		device.setFullScreenWindow(this);
		this.setVisible(true);
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.addKeyListener(keyHandler);
		relative = new Dimension(screen.width/1280,screen.height/800);
		ObjectCreator.createPerson();
		ObjectCreator.createRope();
		animate.start();
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.white);
		g.fillRect(0, 0, screen.width, screen.height);
		g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC));
		g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
		g.setColor(Color.black);
		if(!screenshot){
			g.drawString(String.valueOf(MouseHandler.mouseLoc.getX()), 5, 25);
			g.drawString(String.valueOf(MouseHandler.mouseLoc.getY()), 5, 45);
			g.drawString(String.valueOf(currentSlide),5,65);
		}
		ObjectRegistry.paint(g2);
		
	}
	
	public void saveScreen(){
		ImageHandler.saveScreen();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
	}
}
