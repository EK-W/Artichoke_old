package running;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import running.console.Console;
import node.Node;

public class InputHandler implements MouseMotionListener, MouseListener, KeyListener {
	public static Node selected;
	public static Point2D mouseLoc = new Point2D.Double();
	
	@Override
	public void mouseDragged(MouseEvent e) {
		//mouseLoc.setLocation(e.getX()/Main.scaleX,e.getY()/Main.scaleY);
		mouseLoc.setLocation((e.getX()-Main.xOffset/2)/Main.paintScale,(e.getY()-Main.yOffset/2)/Main.paintScale);
		if(selected!=null)
		selected.updateAsSelected();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		//mouseLoc.setLocation(e.getX()/Main.scaleX,e.getY()/Main.scaleY);
		//mouseLoc.setLocation(e.getX(),e.getY());
		//mouseLoc.setLocation((e.getX()/Main.paintScale)-Main.xOffset/2,(e.getY()/Main.paintScale)+Main.yOffset/2);
		mouseLoc.setLocation((e.getX()-Main.xOffset/2)/Main.paintScale,(e.getY()-Main.yOffset/2)/Main.paintScale);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
			System.out.println("Quit key pressed, quitting.");
			System.exit(0);
		}
		if(Console.commandInputOpen){
			Console.keyInput(e);
			return;
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER)Console.commandInputOpen=true;
		if(e.getKeyCode()==KeyEvent.VK_SLASH){
			Console.commandInputOpen=true;
			Console.sendChar('/');
		}
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
		if(e.getButton()==MouseEvent.BUTTON1){
			//TEMPORARY
			Main.temp.checkSelected();
		}
		if(e.getButton()==MouseEvent.BUTTON2){
			
		}
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		selected=null;
		
	}

}
