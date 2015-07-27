package running.console;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandReader {
	
	public static HashMap<String,Boolean> menuMap = new HashMap<String,Boolean>();
	
	public static void sendCommand(String cmd){
		if(cmd.charAt(1)=='/'){
			cmd=cmd.substring(1);
		}
		Scanner scan = new Scanner(cmd);
		boolean setTo;
		String temp = scan.next();
		if(temp.equalsIgnoreCase("show")){
			setTo=true;
		}else{
			if(temp.equalsIgnoreCase("hide")){
				setTo=false;
			}else{
				scan.close();
				return;
			}
		}
		temp=scan.next();
		for(Map.Entry<String, Boolean> i : menuMap.entrySet()){
			if(i.getKey().equalsIgnoreCase(temp)){
				i.setValue(setTo);
			}
		}
//		if(temp.equalsIgnoreCase("mouse"))DebugStats.showSimpleMouse=setTo;
//		if(temp.equalsIgnoreCase("selected"))DebugStats.showSelected=setTo;
//		if(temp.equalsIgnoreCase("ruler"))DebugStats.showRuler=setTo;
//		if(temp.equalsIgnoreCase("display"))DebugStats.showDisplayInfo=setTo;
		scan.close();
	}
	
//	public static void readCommand(String cmd){
//		
//	}
}
