package running;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import running.displayPanels.AnimationPanel;
import running.displayPanels.DisplayPanel;
import running.input.InputHandler;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2912746798894003330L;
	public static Dimension screen = new Dimension(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
	public static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	public static final double paintScale;
	public static final double xOffset;
	public static final double yOffset;
	public static final Dimension baseRes = new Dimension(1920,1080);
	public static final Dimension baseRat = new Dimension(16, 9);
	public static DisplayPanel panel = new AnimationPanel();
	public static final Font defaultFont = new Font("Times New Roman", Font.PLAIN, 20);
	InputHandler inputHandler = new InputHandler();
	
	static {
		//Get Screen Setup:
		if(screen.getWidth()/baseRat.getWidth()==screen.getHeight()/baseRat.getHeight()){
			paintScale=screen.getWidth()/baseRes.getWidth();
			xOffset=0;
			yOffset=0;
		}else
		if(screen.getWidth()/baseRat.getWidth()>screen.getHeight()/baseRat.getHeight()){
			paintScale=screen.getHeight()/baseRes.getHeight();
			xOffset=screen.getWidth()-((screen.getHeight()/baseRat.getHeight())*baseRat.getWidth());
			yOffset=0;
			
		}else{
			paintScale=screen.getWidth()/baseRes.getWidth();
			xOffset=0;
			yOffset=screen.getHeight()-((screen.getWidth()/baseRat.getWidth())*baseRat.getHeight());
		}
		//Screen Setup Gotten.
	}
	
	public static void main(String[] args){
		@SuppressWarnings("unused")
		Main m = new Main();
	}
	
	public Main(){
		this.setBackground(new Color(204,204,204));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setSize(new Dimension(1280,800));
		this.setFocusable(true);
		device.setFullScreenWindow(this);
		this.addKeyListener(inputHandler);
		this.addMouseListener(inputHandler);
		this.addMouseMotionListener(inputHandler);
		this.setVisible(true);
		this.add(panel);
	}
	
	public void paint(Graphics g){
		this.paintComponents(g);
	}
}
