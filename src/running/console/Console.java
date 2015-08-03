package running.console;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import running.Main;

public class Console {
	public static boolean commandInputOpen = false;
	private static String command = "";
	private static final Dimension inputBox = new Dimension((int) Main.baseRes.getWidth(),35);
	
//	if(cursorCounter<cursorDelay)return command+'_';
//	else return command;
	
	public static String getCommandText(){
		return command;
	}
	
	public static void keyInput(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			CommandReader.sendCommand(command);
			commandInputOpen=false;
			command="";
			return;
		}
		//if(e.getKeyCode()==KeyEvent.VK_BACK_SLASH||e.getKeyChar()==KeyEvent.CHAR_UNDEFINED)return;
		if(e.getKeyChar()!=KeyEvent.CHAR_UNDEFINED){
			if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
				if(command.length()!=0)
				command=command.substring(0, command.length()-1);
			}else{
				command+=e.getKeyChar();
			}
			
		}
		
	}
	
	public static void paint(Graphics2D g){
		DebugStats.paintStats(g);
		if(!commandInputOpen) return;
		g.setColor(new Color(168,168,168));
		g.fill(new Rectangle2D.Double(0,Main.baseRes.getHeight()-inputBox.getHeight(),inputBox.getWidth(),inputBox.getHeight()));
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(3));
		g.draw(new Rectangle2D.Double(0,Main.baseRes.getHeight()-inputBox.getHeight(),inputBox.getWidth(),inputBox.getHeight()));
		g.drawString(command+"_", 10, (int) (Main.baseRes.getHeight()-10));
	}

	public static void sendChar(char c) {
		command+=c;
	}
}
