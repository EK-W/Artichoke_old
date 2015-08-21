//package running.input.console;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public class CommandReader {
//	
//	private static HashMap<String, Command> menuMap = new HashMap<String, Command>();
//	
//	public static void addCommand(String str, Command cmd){
//		menuMap.put(str.toLowerCase(), cmd);
//	}
//	
//	public static void sendCommand(String cmd){
//		System.out.println("Command sent: \"" + cmd + "\"");
//		cmd = cmd.toLowerCase();
//		if(cmd.length() <= 0){
//			return;
//		}
//		if(cmd.charAt(0)=='/'){
//			cmd=cmd.substring(1);
//		}
//		Scanner scan = new Scanner(cmd);
//		String temp = scan.next();
//		if(menuMap.containsKey(temp)){
//			menuMap.get(temp).execute(scan);
//		} else {
//			System.out.println("----no command found.");
//		}
//		scan.close();
//	}
//	
////	public static void readCommand(String cmd){
////		
////	}
//}
