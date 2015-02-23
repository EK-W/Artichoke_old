package display;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import running.Main;
import running.MouseHandler;



public class Display extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2912746798894003330L;
	public static Dimension screen = new Dimension(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
	public static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	public Timer animate = new Timer(50,this);

	public static boolean screenshot = false;
	//public static int currentSlide = 1;
	
	public Display(){
		this.setBackground(new Color(204,204,204));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setSize(screen);
		this.setFocusable(true);
		device.setFullScreenWindow(this);
		this.setVisible(true);
		animate.start();
		//Placeholders


	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.white);
		g.fillRect(0, 0, screen.width, screen.height);
		g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC));
		g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
		
		paintAnimationScreen(g2);
		
	}
	

	private void paintAnimationScreen(Graphics2D g2) {
		if(!screenshot){
			Main.project.drawPreviousSlide(g2);
			g2.setColor(new Color(1f,1f,1f,0.5f));
			g2.fillRect(0, 0, screen.width, screen.height);
			if(CommandLine.commandInputOpen){
				g2.setColor(new Color(199,199,199));
				g2.fillRect(1, 10, 639, 20);
				
			}
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(1));
			g2.drawRect(1, 10, 639, 20);
			
			g2.drawString(String.valueOf(CommandLine.getCommandText()),5,25);
			g2.drawString(String.valueOf(MouseHandler.mouseLoc.getX()), 5, 45);
			g2.drawString(String.valueOf(MouseHandler.mouseLoc.getY()), 5, 65);
			g2.drawString(String.valueOf(Main.project.slideNumber),5,85);
		}

		Main.project.slides.get(Main.project.slideNumber).paint(g2);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
