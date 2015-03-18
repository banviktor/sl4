package phoebe;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	private static int indentationDepth = 0;
	private static boolean logToFile = false;
	private static boolean logToConsole = false;
	private static boolean prefixWithTimestamp = false;
	private static DateFormat timestampFormat = new SimpleDateFormat("HH:mm:ss.SSS");;
	
	private static void indent(){ ++indentationDepth; }
	private static void unindent(){ --indentationDepth; }
	
	public static void setConsoleLogging(boolean value){ logToConsole = value; }
	public static void setTimestampPrefix(boolean value){ prefixWithTimestamp = value; }	
	
	/**
	 * Ki�r egy sort indent�lva, id�b�lyeggel (be�ll�t�st�l f�gg�en) a konzolra
	 * @param msg Az �zenet
	 */
	public static void writeLine(String msg){		
		//Indent�l�s
		for(int i = 0; i < indentationDepth; ++i){
			msg = "  " + msg;
		}
		
		//Id�b�lyeg hozz�f�z�se az �zenet elej�hez, ha kell
		if(prefixWithTimestamp){
			msg = "[" + timestampFormat.format(new Date()) + "] " + msg;
		}
		
		//Konzolra �r�s, ha kell
		if(logToConsole){
			System.out.println(msg);
		}
		
		//F�jlba �r�s, ha kell
		if(logToFile){
			//TODO f�jlba �r�s
		}
	}	

	/**
	 * Egy f�ggv�nybe val� l�p�skor megh�vand� met�dus, mely ki�rja a bel�p�s param�tereit.
	 * @param classId A f�ggv�nyt tartalmaz� oszt�ly
	 * @param function A f�ggv�ny neve
	 * @param args A f�ggv�ny param�terlist�ja sz�vegesen
	 */ 
	public static void enterFunction(Class<?> classId, String function, String args){		
		writeLine("-> [" + classId.getSimpleName() + "]: " + function + "(" + args + ")");
		indent();
	}
	
	/**
	 * Egy f�ggv�nybe val� l�p�skor megh�vand� met�dus, mely ki�rja a bel�p�s param�tereit.
	 * @param classId A f�ggv�nyt tartalmaz� oszt�ly
	 * @param function A f�ggv�ny neve
	 */ 
	public static void enterFunction(Class<?> classId, String function){
		enterFunction(classId, function, "");
	}
	
	/**
	 * Egy f�ggv�nyb�l val� kil�p�s el�tt megh�vand� met�dus, mely ki�rja a kil�p�s param�tereit.
	 * @param returnValue A visszat�r�si �rt�k
	 */
	public static void exitFunction(Object returnValue){
		unindent();
		writeLine("<- " + returnValue.toString());
	}
	
	/**
	 * Egy f�ggv�nyb�l val� kil�p�s el�tt megh�vand� met�dus, mely ki�rja a kil�p�s param�tereit.
	 */
	public static void exitFunction(){
		exitFunction("");
	}
}
