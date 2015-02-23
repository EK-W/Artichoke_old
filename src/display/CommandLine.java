package display;

import java.awt.event.KeyEvent;

import running.Main;

public class CommandLine {
	public static boolean commandInputOpen = false;
	private static String command = "";
	
	public static String getCommandText(){
		return command+'_';
	}
	
	public static void keyInput(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			executeCommand();
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
	public static void sendChar(char c){
		command+=c;
	}
	private static void executeCommand(){
		findCommands();
		command="";
		commandInputOpen=false;
	}
	private static void findCommands(){
		if(command.equalsIgnoreCase("/save")){
			Main.project.save();
			return;
		}
		if(command.equalsIgnoreCase("/addSlide")){
			Main.project.addNewSlide();
			return;
		}
		
		
		System.out.println(command + " does not match any of the commands available to execute.");
	}

}
