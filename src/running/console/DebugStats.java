package running.console;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import bodies.Node;
import running.InputHandler;
import running.Main;

public class DebugStats {
	private static int yLoc = 25;
	static{
		CommandReader.menuMap.put("mouse", false);
		CommandReader.menuMap.put("selected", false);
		CommandReader.menuMap.put("ruler", false);
		CommandReader.menuMap.put("display", false);
	}
	public static void paintStats(Graphics2D g){
		g.setColor(Color.black);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
//		if(showSimpleMouse)paintSimpleMouse(g);
//		if(showSelected)paintSelected(g);
//		if(showRuler)paintRuler(g);
		if(CommandReader.menuMap.get("mouse"))paintSimpleMouse(g);
		if(CommandReader.menuMap.get("selected"))paintSelected(g);
		if(CommandReader.menuMap.get("ruler"))paintRuler(g);
		if(CommandReader.menuMap.get("display"))paintDisplayInfo(g);
		yLoc=25;
	}
	private static void paintSimpleMouse(Graphics2D g){
		g.drawString("MouseX:  " + Math.round(InputHandler.mouseLoc.getX()),50,yLoc);yLoc+=20;
		g.drawString("MouseY:  " + Math.round(InputHandler.mouseLoc.getY()), 50, yLoc);yLoc+=20;
	}
	private static void paintSelected(Graphics2D g){
		Node temp = InputHandler.selected;
		g.drawString("Selected: " + (temp == null?"null":InputHandler.selected.toString()), 50, yLoc);yLoc+=20;
		
		g.drawString("Selected children#: " + (temp == null || temp.getChild() == null? "null" : temp.getChild().length()), 50, yLoc);yLoc+=20;
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
