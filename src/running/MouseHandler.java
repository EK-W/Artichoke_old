package running;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import objects.ObjectRegistry;
import objects.sections.ConnectionPoint;


public class MouseHandler implements MouseListener, MouseMotionListener{

	public static ConnectionPoint selected;
	public static Point2D mouseLoc = new Point2D.Double(0,0);
	public static boolean mouseDown = false;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseLoc = e.getPoint();
		if(mouseDown&&selected!=null){
			selected.updateAsSelected();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseLoc = e.getPoint();
//		if(mouseDown){
//			ObjectRegistry.update();
//		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseDown=true;
		selected=null;
		ObjectRegistry.checkMouseClick();
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseDown=false;
		
	}

}
