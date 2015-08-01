package running.console;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandReader {
	
	public static HashMap<String, Command> menuMap = new HashMap<String, Command>();
	
	
	
	public static void sendCommand(String cmd){
		if(cmd.length() <= 0){
			return;
		}
		if(cmd.charAt(0)=='/'){
			cmd=cmd.substring(1);
		}
		Scanner scan = new Scanner(cmd);
		String temp = scan.next();
		if(menuMap.containsKey(temp)){
			menuMap.get(temp).execute(scan);
		}
		scan.close();
	}
	
//	public static void readCommand(String cmd){
//		
//	}
}
