package running;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.Timer;

import node.Node;

public class Main extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2912746798894003330L;
	public static Dimension screen = new Dimension(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
	public static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	public Timer animate = new Timer(50,this);
//	public static final double scaleY = screen.getHeight()/1080d;
//	public static final double scaleX = screen.getWidth()/1920d;
	public static final double paintScale;
	public static final double xOffset;
	public static final double yOffset;
	public static final Dimension baseRes = new Dimension(1920,1080);
	public static final Dimension baseRat = new Dimension(16,9);
	InputHandler inputHandler = new InputHandler();
	
	//TEMPORARY
			static Node temp = new Node(new Point2D.Double(640, 400));
			static Node temp2 = new Node(temp, 135, 50);
			static Node temp3a = new Node(temp2, 135, 50);
			static Node temp3b = new Node(temp2, -135, 50);
	
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
	
	Display panel = new Display();
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
		animate.start();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public void paint(Graphics g){
		this.paintComponents(g);
	}
}
