package running.console;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Scanner;

import bodies.Slide;
import running.InputHandler;
import running.Main;
import util.GifSequenceWriter;

public class DebugStats {
	private static int yLoc = 25;
	static boolean paintMouseInfo = false;
	static boolean paintSelectedInfo = true;
	static boolean paintRuler = false;
	static boolean paintDisplayInfo = false;
	static boolean paintSlideInfo = true;
	
	static{
		CommandReader.menuMap.put("debugSlide", (Scanner sc) -> paintSlideInfo = !paintSlideInfo);
		CommandReader.menuMap.put("debugMouse", (Scanner sc) -> paintMouseInfo = !paintMouseInfo);
		CommandReader.menuMap.put("debugSelected", (Scanner sc) -> paintSelectedInfo = !paintSelectedInfo);
		CommandReader.menuMap.put("debugRuler", (Scanner sc) -> paintRuler = !paintRuler);
		CommandReader.menuMap.put("debugDisplay", (Scanner sc) -> paintDisplayInfo = !paintDisplayInfo);
		CommandReader.menuMap.put("addSlide", (Scanner sc) -> Slide.addSlide());
		CommandReader.menuMap.put("export", (Scanner sc) -> {
			if(sc.hasNextInt()){
				GifSequenceWriter.writeGif(sc.nextInt());
			} else {
				GifSequenceWriter.writeGif(100);
			}
		});
	}

	public static void paintStats(Graphics2D g){
		g.setColor(Color.black);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		if(paintSlideInfo)paintSlideNum(g);
		if(paintMouseInfo)paintSimpleMouse(g);
		if(paintSelectedInfo)paintSelected(g);
		if(paintRuler)paintRuler(g);
		if(paintDisplayInfo)paintDisplayInfo(g);
		yLoc=25;
	}
	private static void paintSlideNum(Graphics2D g) {
		g.drawString("Slide Number: " + Math.round(Slide.getSlideNum()),50,yLoc);yLoc+=20;
		g.drawString("Slides Size: " + Math.round(Slide.getSlideAmt()),50,yLoc);yLoc+=20;
		
	}
	private static void paintSimpleMouse(Graphics2D g){
		g.drawString("MouseX: " + Math.round(InputHandler.mouseLoc.getX()),50,yLoc);yLoc+=20;
		g.drawString("MouseY: " + Math.round(InputHandler.mouseLoc.getY()), 50, yLoc);yLoc+=20;
	}
	private static void paintSelected(Graphics2D g){
		if(InputHandler.selected == null || InputHandler.selected.getDebugInfo() == null){
			g.drawString("Selected: null", 50, yLoc);yLoc+=20;
			return;
		}
		g.drawString("Selected: " + InputHandler.selected.toString(), 50, yLoc);yLoc+=20;
		String[] temp = InputHandler.selected.getDebugInfo();
		for(int i = 0; i < temp.length; i++){
			g.drawString(temp[i], 50, yLoc);yLoc+=20;
		}
	}
	private static void paintRuler(Graphics2D g){
		for(int i=0;i<=Main.baseRes.height;i+=25){
			g.drawString(String.valueOf(i),5,i);
			g.fillRect(0,i-1,4,2);
		}
	}
	private static void paintDisplayInfo(Graphics2D g){
		g.drawString("Programmed Resolution: " + Main.baseRes.getWidth()+" x "+Main.baseRes.getHeight(), 50, yLoc);yLoc+=20;
		g.drawString("Native Resolution: " + Main.screen.getWidth()+" x "+Main.screen.getHeight(), 50, yLoc);yLoc+=20;
		g.drawString("Paint Scale: " + Main.paintScale, 50, yLoc);yLoc+=20;
		g.drawString("Offsets: " + Main.xOffset+", "+Main.yOffset, 50, yLoc);yLoc+=20;
	}
}
