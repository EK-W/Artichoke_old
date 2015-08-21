package running.input.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import running.Main;
import running.input.events.GuiEvent;

public class InputBar extends GuiObject{
	int maxChars = Integer.MAX_VALUE;
	String title;
	private static InputBar openBar = null;
	String text = "";
	char[] charFilter;
	
	public static final char[] LETTERS = {
		'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
	};
	public static final char[] INTEGERS = {
		'1','2','3','4','5','6','7','8','9','0'
	};
	public static final char[] FLOATS = {
		'1','2','3','4','5','6','7','8','9','0', '.'
	};
	public static final char[] ALPHANUMERIC = {
		'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
		'1','2','3','4','5','6','7','8','9','0', '.'
	};
	
	public InputBar(String title, Rectangle bounds, int maxChars, char[] charFilter, GuiEvent onCommit){
		super(bounds, onCommit);
		this.title = title;
		this.maxChars = maxChars;
		this.charFilter = charFilter;
	}
	
	@Override
	public void execute(){
		String ret = "";
		if(text.length() > maxChars) {
			text = text.substring(0, maxChars);
		}
		for(int i = 0; i < text.length(); i++){
			if(containsChar(charFilter, text.charAt(i))){
				ret += text.charAt(i);
			}
		}
		text = ret;
		toExecute.execute(ret);
	}
	
	private boolean containsChar(char[] array, char entry){
		for(char i: array){
			if(i == Character.toLowerCase(entry)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void paint(Graphics2D g){
		if(!enabled) return;
		g.setColor(new Color(204, 252, 255));
		g.fill(bounds);
		g.setColor(new Color(102, 102, 102));
		g.draw(bounds);
		g.setColor(Color.black);
		//Every half second the string cursor will toggle. Undoable because the program doesn't repaint at a constant rate.
//		g.drawString(title + ' ' + text + ((System.nanoTime() / 500000000) % 2 == 0 && open? "|" : ""), 
//			location.x + (width / 2) - (g.getFontMetrics().stringWidth(title + ' ' + text) / 2), 
//			location.y + (height / 2) + (g.getFontMetrics().getAscent() / 2));
		g.drawString(title + ' ' + text + (openBar == this? "_" : ""), 
				bounds.x + (bounds.width / 2) - (g.getFontMetrics().stringWidth(title + ' ' + text) / 2), 
				bounds.y + (bounds.height / 2) + (g.getFontMetrics().getAscent() / 2));
	}
	
	@Override
	public InputBar setEnabled(boolean enabled){
		this.enabled = enabled;
		if(openBar == this && !enabled) openBar = null;
		return this;
	}
	
	public void setOpen(){
		if(!enabled) return;
		if(openBar != null && openBar != this){
			openBar.execute();
		}
		openBar = this;
	}
	
	public void setClosed(){
		if(openBar == this){
			execute();
			openBar = null;
		}
	}
	
	public static void open(InputBar toOpen){
		if(!toOpen.enabled) return;
		if(openBar != null && openBar != toOpen){
			openBar.execute();
		}
		openBar = toOpen;
	}
	
	public static void close(){
		openBar = null;
	}
	
	public static void sendChar(char c){
		if(openBar == null)
			return;
		if(openBar.text.length() < openBar.maxChars)
			openBar.text += c;
	}
	public static void backspace(){
		if(openBar == null)
			return;
		if(openBar.text.length() > 0)
			openBar.text = openBar.text.substring(0, openBar.text.length() - 1);
	}
	public static void clear(){
		if(openBar == null)
			return;
		openBar.text = "";
	}
	public static InputBar getOpenBar(){
		return openBar;
	}

	public static void sendKeyEvent(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			backspace();
		} if(e.getKeyChar() != KeyEvent.CHAR_UNDEFINED){
			sendChar(e.getKeyChar());
		}
	}
}
