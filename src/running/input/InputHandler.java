package running.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import javax.swing.SwingUtilities;

import running.Main;
import running.input.console.Console;

public class InputHandler implements MouseMotionListener, MouseListener, KeyListener{
	//These variables exist for "debugging" purposes only
	private static Point2D mouseLoc = new Point2D.Double();
	private static boolean leftMouse = false;
	private static boolean rightMouse = false;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseLoc.setLocation((e.getX()-Main.xOffset/2)/Main.paintScale,(e.getY()-Main.yOffset/2)/Main.paintScale);
		Main.panel.onMouseMove(mouseLoc, true);
		Main.panel.repaint();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseLoc.setLocation((e.getX()-Main.xOffset/2)/Main.paintScale,(e.getY()-Main.yOffset/2)/Main.paintScale);
		Main.panel.onMouseMove(mouseLoc, false);
		Main.panel.repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
			System.out.println("Quit key pressed, quitting.");
			System.exit(0);
		}
		if(Console.commandInputOpen){
			Console.keyInput(e);
		} else if(e.getKeyCode()==KeyEvent.VK_ENTER){
			Console.commandInputOpen=true;
		} else if(e.getKeyCode()==KeyEvent.VK_SLASH){
			Console.commandInputOpen=true;
			Console.sendChar('/');
		} else {
			Main.panel.onKeyPress(e);
		}
		Main.panel.repaint();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		mouseLoc.setLocation((e.getX()-Main.xOffset/2)/Main.paintScale,(e.getY()-Main.yOffset/2)/Main.paintScale);
		Main.panel.onMousePress(mouseLoc, e.getButton());
		if(SwingUtilities.isLeftMouseButton(e))leftMouse = true;
		if(SwingUtilities.isRightMouseButton(e))rightMouse = true;
		Main.panel.repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		mouseLoc.setLocation((e.getX()-Main.xOffset/2)/Main.paintScale,(e.getY()-Main.yOffset/2)/Main.paintScale);
		Main.panel.onMouseRelease(mouseLoc, e.getButton());
		if(SwingUtilities.isLeftMouseButton(e))leftMouse = false;
		if(SwingUtilities.isRightMouseButton(e))rightMouse = false;
		Main.panel.repaint();
	}

	public static String[] getDebugInfo() {
		return new String[]{
			("Mouse location: X: " + mouseLoc.getX() + " Y: " + mouseLoc.getY()),
			("Mouse buttons down: " + (leftMouse? "left " : "") + (rightMouse? "right " : ""))
		};
	}

}
