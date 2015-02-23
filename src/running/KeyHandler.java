package running;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import display.CommandLine;

public class KeyHandler implements KeyListener{
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(CommandLine.commandInputOpen){
			CommandLine.keyInput(e);
			return;
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER)CommandLine.commandInputOpen=true;
		if(e.getKeyCode()==KeyEvent.VK_SLASH){
			CommandLine.commandInputOpen=true;
			CommandLine.sendChar('/');
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_RIGHT){
			Main.project.setSituation(e.getKeyCode());
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(CommandLine.commandInputOpen)return;
		//if(e.getKeyCode()==KeyEvent.VK_SPACE)Main.project.save();
		//if(e.getKeyCode()==KeyEvent.VK_S)ImageHandler.saveToGif();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
