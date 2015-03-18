package phoebe;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Log {
	private static int indentationDepth = 0;
	private static boolean logToFile = false;
	private static boolean logToConsole = false;
	private static boolean prefixWithTimestamp = false;
	
	private static void indent(){ ++indentationDepth; }
	private static void unindent(){ --indentationDepth; }
	
	public static void setConsoleLogging(boolean value){ logToConsole = value; }
	public static void setTimestampPrefix(boolean value){ prefixWithTimestamp = value; }

	
	
	private static void writeLine(String msg){
		
		//Indent�l�s
		for(int i = 0; i < indentationDepth; ++i){
			msg = "   " + msg;
		}
		
		//Id�b�lyeg hozz�f�z�se az �zenet elej�hez, ha kell
		if(prefixWithTimestamp){
			msg = "[07:29:13] " + msg;
		}
		
		//Konzolra �r�s, ha kell
		if(logToConsole){
			System.out.println(msg);
		}
		
		//F�jlba �r�s, ha kell
		if(logToFile){
			
		}
	}	

	public static void enterFunction(Class<?> object, String function, String args){		
		writeLine("-> [" + object.getSimpleName() + "]: " + function + "(" + args + ")");
		indent();
	}
	
	public static void enterFunction(Class<?> object, String function){
		enterFunction(object, function, "");
	}
	
	public static void exitFunction(String returnValue){
		unindent();
		writeLine("<- " + returnValue);
	}
	
	public static void exitFunction(){
		exitFunction("");
	}
}
