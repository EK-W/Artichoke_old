package display;

import java.awt.event.KeyEvent;
import java.util.Scanner;

import objects.ObjectCreator;
import objects.Slide;
import running.Main;
import running.Project;

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
		Scanner s = new Scanner(command);
		if(!s.hasNext())return;
		String next = s.next();
		if(command.equalsIgnoreCase("/save")){
			Main.project.save();
			return;
		}
		if(next.equalsIgnoreCase("/save")){
			Main.project.save(s.nextInt());
			return;
		}
		if(command.equalsIgnoreCase("/addSlide")){
			Main.project.addNewSlide();
			Main.project.setSlide(Main.project.slides.size()-1);
			return;
		}
		if(next.equalsIgnoreCase("/deleteSlide")){
			Main.project.slides.remove(s.nextInt());
			return;
		}
		if(command.equalsIgnoreCase("/setPrevious")){
			if(Main.project.slideNumber>0)
			Main.project.slides.set(Main.project.slideNumber, Main.project.slides.get(Main.project.slideNumber-1).clone());
			return;
		}
		if(next.equalsIgnoreCase("/load")){
			Main.project=new Project(s.next());
			if(Main.project.slides.size()==0){
				Main.project.slides.add(new Slide());
				Main.project.slides.get(Main.project.slideNumber).addObject(ObjectCreator.createPerson());
			}
			return;
		}
//		if(next.equalsIgnoreCase("/copy")){
//			
//			return;
//		}
		
		
		
		System.out.println("\""+command + "\" does not match any of the commands available to execute.");
	}

}
