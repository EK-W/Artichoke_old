//package running.input.console;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics2D;
//import java.awt.geom.Point2D;
//import java.util.Scanner;
//
//import nodes.bodies.Node;
//import nodes.util.AngleMath;
//import nodes.util.BodyBuilder;
//import running.Main;
//import running.displayPanels.AnimationPanel;
//import running.input.InputHandler;
//import util.GifSequenceWriter;
//
//public class DebugStats {
//	private static int yLoc = 25;
//	private static int xLoc = 5;
//	static boolean paintInputInfo = true;
//	static boolean paintPanelInfo = true;
//	static boolean paintRuler = false;
//	static boolean paintDisplayInfo = false;
//	
//	static{
//		CommandReader.addCommand("debugInput", (Scanner sc) -> paintInputInfo = !paintInputInfo);
//		CommandReader.addCommand("debugPanel", (Scanner sc) -> paintPanelInfo = !paintPanelInfo);
//		CommandReader.addCommand("ruler", (Scanner sc) -> paintRuler = !paintRuler);
//		CommandReader.addCommand("debugDisplay", (Scanner sc) -> paintDisplayInfo = !paintDisplayInfo);
//		CommandReader.addCommand("addSlide", (Scanner sc) -> AnimationPanel.addSlide());
//		CommandReader.addCommand("export", (Scanner sc) -> {
//			if(sc.hasNextInt()){
//				GifSequenceWriter.writeGif(sc.nextInt());
//			} else {
//				GifSequenceWriter.writeGif(100);
//			}
//		});
//		CommandReader.addCommand("addPreset", (Scanner sc) -> readPreset(sc));
//		CommandReader.addCommand("removeBody", (Scanner sc) -> AnimationPanel.removeSelectedBody());
//		CommandReader.addCommand("set", (Scanner sc) -> readSet(sc));
//	}
//	
//	
//
//	public static void paintStats(Graphics2D g){
//		g.setColor(Color.black);
//		
//		if(paintRuler)paintRuler(g);
//		if(paintPanelInfo)paintPanelInfo(g);
//		if(paintInputInfo)paintInputInfo(g);
//		if(paintDisplayInfo)paintDisplayInfo(g);
//		
//		
//		yLoc=25;
//		xLoc = 5;
//	}
//	private static void readSet(Scanner sc) {
//		if(!sc.hasNext()) return;
//		String temp = sc.next();
//		if(AnimationPanel.selected instanceof Node){
//			Node selected = (Node) AnimationPanel.selected;
//			if(temp.equalsIgnoreCase("angle")){
//				double angle = 180;
//				if(sc.hasNextDouble())
//					angle = sc.nextDouble();
//				selected.angle = angle;
//				selected.update();
//			} else if(temp.equalsIgnoreCase("length")){
//				double length = 50;
//				if(sc.hasNextDouble())
//					length = sc.nextDouble();
//				selected.length = length;
//				selected.update();
//			} else if(temp.equalsIgnoreCase("thickness")){
//				float thickness = 1;
//				if(sc.hasNextFloat())
//					thickness = sc.nextFloat();
//				selected.lineThickness = thickness;
//			} else if(temp.equalsIgnoreCase("color")){
//				int r = 0;
//				int g = 0;
//				int b = 0;
//				if(sc.hasNextInt())
//					r = sc.nextInt();
//				if(sc.hasNextInt())
//					g = sc.nextInt();
//				if(sc.hasNextInt())
//					b = sc.nextInt();
//				selected.lineColor = new Color(r, g, b);
//			}
//				
//		}
//	}
//	private static void readPreset(Scanner sc) {
//		if(!sc.hasNext()) return;
//		String type = sc.next();
//		if(type.equalsIgnoreCase("person")){
//			int x = (int) (Main.baseRes.getWidth()/2);
//			int y = (int) (Main.baseRes.getHeight()/2);
//			double scale = 1;
//			if(sc.hasNextDouble())
//				scale = sc.nextDouble();
//			if(sc.hasNextInt())
//				x = sc.nextInt();
//			if(sc.hasNextInt())
//				y = sc.nextInt();
//			AnimationPanel.addBody(BodyBuilder.makePerson(new Point2D.Double(x, y), scale));
//		} else if(type.equalsIgnoreCase("polygon")){
//			int x = (int) (Main.baseRes.getWidth()/2);
//			int y = (int) (Main.baseRes.getHeight()/2);
//			int amt = 5;
//			double length = 75;
//			if(sc.hasNextInt())
//				amt = sc.nextInt();
//			if(sc.hasNextDouble())
//				length = sc.nextDouble();
//			if(sc.hasNextInt())
//				x = sc.nextInt();
//			if(sc.hasNextInt())
//				y = sc.nextInt();
//			AnimationPanel.addBody(BodyBuilder.makePolygon(new Point2D.Double(x, y), length, amt));
//		}
//	}
//	private static void paintInputInfo(Graphics2D g){
//		String[] temp = InputHandler.getDebugInfo();
//		for(String i: temp){
//			g.drawString(i, xLoc, yLoc);
//			yLoc+=20;
//		}
//	}
//	
//	private static void paintPanelInfo(Graphics2D g){
//		String[] temp = Main.panel.getDebugInfo();
//		for(String i: temp){
//			g.drawString(i, xLoc, yLoc);
//			yLoc+=20;
//		}
//	}
//	private static void paintRuler(Graphics2D g){
//		for(int i=0;i<=Main.baseRes.height;i+=25){
//			g.drawString(String.valueOf(i),5,i);
//			g.fillRect(0,i-1,4,2);
//		}
//		xLoc = 50;
//	}
//	private static void paintDisplayInfo(Graphics2D g){
//		g.drawString("Programmed Resolution: " + Main.baseRes.getWidth()+" x "+Main.baseRes.getHeight(), xLoc, yLoc);yLoc+=20;
//		g.drawString("Native Resolution: " + Main.screen.getWidth()+" x "+Main.screen.getHeight(), xLoc, yLoc);yLoc+=20;
//		g.drawString("Paint Scale: " + Main.paintScale, xLoc, yLoc);yLoc+=20;
//		g.drawString("Offsets: " + Main.xOffset+", "+Main.yOffset, xLoc, yLoc);yLoc+=20;
//	}
//}
