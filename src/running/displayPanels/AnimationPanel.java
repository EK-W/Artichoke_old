package running.displayPanels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import running.Main;
import running.input.Button;
import nodes.bodies.Body;
import nodes.bodies.Selectable;

public class AnimationPanel extends DisplayPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4312883470921417750L;
	public static Selectable selected;
	private static ArrayList<Button> buttons = new ArrayList<Button>();
	//ArrayList because it will be referenced often. It will also be added to often so maybe this is a bad idea...
	private static ArrayList<Slide> slides = new ArrayList<Slide>();
	private static int slideNum = 0;
	
	static {
		buttons.add(new Button("Add Slide", new Rectangle2D.Double(Main.baseRes.getWidth() - 100, 0, 100, 50), true, () ->{
			addSlide();
		}));
		
	}
	
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
		for(Button i: buttons){
			i.paint(g);
		}
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
	public void onMousePress(Point2D p, int mouseButton) {
		if(mouseButton == MouseEvent.BUTTON1){
			selected = null;
			selected = slides.get(slideNum).checkSelected(p);
			if(selected == null){
				for(Button i: buttons){
					if(i.mouseDown(p)){
						break;
					}
				}
			}
		}
	}

	@Override
	public void onMouseRelease(Point2D p, int mouseButton) {
		//selected = null;
		for(Button i: buttons){
			i.mouseUp();
		}
	}

	@Override
	public void onMouseMove(Point2D p, boolean pressed) {
		if(pressed && selected != null){
			selected.updateAsSelected(p);
		}
	}

	@Override
	public void onKeyPress(KeyEvent e) {
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
