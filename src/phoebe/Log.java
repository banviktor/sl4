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
	 * Kiír egy sort indentálva, idõbélyeggel (beállítástól függõen) a konzolra
	 * @param msg Az üzenet
	 */
	public static void writeLine(String msg){		
		//Indentálás
		for(int i = 0; i < indentationDepth; ++i){
			msg = "  " + msg;
		}
		
		//Idõbélyeg hozzáfûzése az üzenet elejéhez, ha kell
		if(prefixWithTimestamp){
			msg = "[" + timestampFormat.format(new Date()) + "] " + msg;
		}
		
		//Konzolra írás, ha kell
		if(logToConsole){
			System.out.println(msg);
		}
		
		//Fájlba írás, ha kell
		if(logToFile){
			//TODO fájlba írás
		}
	}	

	/**
	 * Egy függvénybe való lépéskor meghívandó metódus, mely kiírja a belépés paramétereit.
	 * @param classId A függvényt tartalmazó osztály
	 * @param function A függvény neve
	 * @param args A függvény paraméterlistája szövegesen
	 */ 
	public static void enterFunction(Class<?> classId, String function, String args){		
		writeLine("-> [" + classId.getSimpleName() + "]: " + function + "(" + args + ")");
		indent();
	}
	
	/**
	 * Egy függvénybe való lépéskor meghívandó metódus, mely kiírja a belépés paramétereit.
	 * @param classId A függvényt tartalmazó osztály
	 * @param function A függvény neve
	 */ 
	public static void enterFunction(Class<?> classId, String function){
		enterFunction(classId, function, "");
	}
	
	/**
	 * Egy függvénybõl való kilépés elõtt meghívandó metódus, mely kiírja a kilépés paramétereit.
	 * @param returnValue A visszatérési érték
	 */
	public static void exitFunction(Object returnValue){
		unindent();
		writeLine("<- " + returnValue.toString());
	}
	
	/**
	 * Egy függvénybõl való kilépés elõtt meghívandó metódus, mely kiírja a kilépés paramétereit.
	 */
	public static void exitFunction(){
		exitFunction("");
	}
}
