package running.displayPanels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import running.Main;
import running.input.console.Console;
import running.input.gui.Button;
import running.input.gui.InputBar;
import running.input.gui.PopupMenu;
import nodes.bodies.Node;
import nodes.bodies.Body;
import nodes.bodies.Selectable;
import nodes.util.BodyBuilder;

public class AnimationPanel extends DisplayPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4312883470921417750L;
	public static Selectable selected;
	
	//ArrayList because it will be referenced often. It will also be added to often so maybe this is a bad idea...
	private static ArrayList<Slide> slides = new ArrayList<Slide>();
	private static int slideNum = 0;
	private static Point2D lastMouse = new Point2D.Double(0, 0);
//	private static Button[] buttons = {
//		new Button(new Rectangle(Main.baseRes.width - 100, 0, 100, 100), new File("assets/AddSlide.png"), 
//				(String str) -> addSlide()),
//	};
//	private static Button[] contextMenu = {
//		new Button(new Rectangle(0, 0, 100, 25), new File("assets/AddNode.png"), (String str) ->{
//			if(selected instanceof Node){
//				selected.getBody().add((Node) selected, new Node(new Point2D.Double(lastMouse.getX(), lastMouse.getY())));
//			} else {
//				selected.getBody().add(new Node(180, 100));
//			}
//		}),
//	};
	
//	private InputBar console = new InputBar("Console: ", new Rectangle(0, Main.baseRes.height - 35, Main.baseRes.width, 35), 999, 
//			InputBar.ALPHANUMERIC, (String str) -> Console.sendCommand(str));
	private PopupMenu selectedContextMenu = new PopupMenu(
			new Button("Add Node", new Rectangle(0, 0, 150, 25), (String str) ->{
				if(selected instanceof Node){
					selected.getBody().add((Node) selected, new Node(new Point2D.Double(lastMouse.getX(), lastMouse.getY())));
				} else {
					selected.getBody().add(new Node(180, 100));
				}
			}),
			new Button("Delete Body", new Rectangle(0, 0, 150, 25), (String str) -> {
				removeSelectedBody();
			})	
	);
	
	private PopupMenu contextMenu = new PopupMenu(
			new InputBar("Add person with scale: ", new Rectangle(0, 0, 200, 25), 2, InputBar.FLOATS, (String str) -> BodyBuilder.makePerson(lastMouse, Float.valueOf(str)))
	);
	
	public AnimationPanel(){
		if(slides.size() == 0){
			addSlide();
		}
	}
	
	@Override
	protected void paint(Graphics2D g){
		if(slideNum > 0){
			slides.get(slideNum - 1).paintMask(g);
			g.setColor(new Color(1f, 1f, 1f, 0.75f));
			g.fill(new Rectangle2D.Double(0, 0, Main.baseRes.getWidth(), Main.baseRes.getHeight()));
		}
		slides.get(slideNum).paint(g);
		if(selected != null){
			g.setColor(new Color(250, 250, 0));
			g.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g.draw(selected.getShape());
		}
		selectedContextMenu.paint(g);
	}
	
	public static void addSlide(){
		slideNum = slides.size();
		if(slides.size() == 0){
			slides.add(new Slide());
		}else{	
			slides.add(new Slide(slides.get(slides.size() - 1).bodies));
		}
	}
	
	public static void addBody(Body b){
		slides.get(slideNum).addBody(b);
	}

	@Override
	public void onMousePress(Point p, int button) {
		lastMouse.setLocation(p);
		if(button == MouseEvent.BUTTON1){
			if(selectedContextMenu.checkClick(p)){
				return;
			}
			selectedContextMenu.close();
			selected = null;
			selected = slides.get(slideNum).checkSelected(p);
		} else if(button == MouseEvent.BUTTON3){
			if(selected == null){
				
			} else {
				selectedContextMenu.open(p);
			}
		}
	}

	@Override
	public void onMouseRelease(Point p, int button) {
		
	}

	@Override
	public void onMouseMove(Point p, boolean pressed) {
		if(pressed && selected != null){
			selected.updateAsSelected(p);
		}
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		if(InputBar.getOpenBar() != null){	
			InputBar.sendKeyEvent(e);
			return;
		} 
		if(e.getKeyCode()==KeyEvent.VK_LEFT && slideNum > 0){
			slideNum--;
			selected = null;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT && slideNum < slides.size() - 1){
			slideNum++;
			selected = null;
		}
	}
	
	public static BufferedImage[] toImgArray(){
		BufferedImage[] ret = new BufferedImage[slides.size()];
		for(int i = 0; i < ret.length; i++){
			ret[i] = new BufferedImage((int)Main.baseRes.getWidth(), (int)Main.baseRes.getHeight(), BufferedImage.TYPE_INT_RGB);
		}
		for(int i = 0; i < slides.size(); i++){
			Graphics2D g = (Graphics2D) ret[i].getGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, (int)Main.baseRes.getWidth(), (int)Main.baseRes.getHeight());
			for(int j = 0; j < slides.get(i).bodies.size(); j++){
				slides.get(i).bodies.get(j).paintImg(g);
			}
		}
		return ret;
	}
	
	public static void removeSelectedBody(){
		if(selected != null){
			slides.get(slideNum).deleteBody(selected.getBody());
			selected = null;
		}
	}

	@Override
	public String[] getDebugInfo() {
		if(selected == null){
			return new String[]{
					("Slide number: " + slideNum + " / " + (slides.size() - 1)),
					("Selected: null")
			};
		}else{
			String[] temp = selected.getDebugInfo();
			String[] ret = new String[temp.length + 1];
			ret[0] = ("Slide number: " + slideNum + " / " + (slides.size() - 1));
			
			for(int i = 0; i < temp.length; i++){
				ret[i + 1] = temp[i];
			}
			return ret;
		}
	}
	
}
