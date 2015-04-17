package phoebe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import phoebe.basic.Vector;

public class UserIO {
	
	private static boolean randomization = true;
	private static boolean questions = true;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static PrintStream out = null;
	
	static {
		try {
			out = new PrintStream(System.out, true, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void ask(String question, boolean newline){
		if (newline)
			question += "\n";
		if (questions)
			print(question);
	}
	
	public static void ask(String question){
		if (questions)
			ask(question, false);
	}
	
	public static void print(String s) {
		out.print(s);
	}
	
	public static void println(String s) {
		out.println(s);
	}
	
	public static boolean getBoolean(String question, boolean defaultValue){
		if(randomization)
			return defaultValue;	
		while(true){
			ask(question + " (I/N) : ");
			try{
	            String i = br.readLine().toLowerCase();
	            if(i.equals("i"))
	            	return true;
	            else if(i.equals("n"))
	            	return false;	     
	        } catch (IOException e) {
	        	
			}
		}
	}
	
	public static int getInt(String question, int defaultValue){
		if(randomization)
			return defaultValue;
		while(true){
			ask(question + " (#) : ");
			try{
	            int i = Integer.parseInt(br.readLine());
	            return i;
	        } catch(NumberFormatException nfe){
	            
	        } catch (IOException e) {
	        	
			}
		}
	}
	
	public static Vector getVector(String question, Vector defaultValue) {
		Vector vec = defaultValue;
		if(randomization)
			return defaultValue;
		while(true){
			ask(question + " (#) (#) : ");
			try{
				String[] line = br.readLine().split(" ");
				vec = new Vector(Double.parseDouble(line[0]), Double.parseDouble(line[1]));
				return vec;
	        } catch(NumberFormatException nfe){
	            
	        } catch (IOException e) {
	        	
			}
		}
	}
	
	public static String[] getCommand(){
		try{
            String i = br.readLine();
            i = i.toLowerCase();
            return i.split(" ");
        } catch (IOException e) {
        	return new String[]{};
		} 	
	}
	
	public static void randomOn(){
		println("Véletlenszerű események bekapcsolva.");
		randomization = true;
	}
	
	public static void randomOff(){
		println("Véletlenszerű események kikapcsolva.");
		randomization = false;
	}
	
	public static void questionsOn(){
		println("Kérdezés bekapcsolva.");
		questions = true;
	}
	
	public static void questionsOff(){
		println("Kérdezés kikapcsolva.");
		questions = false;
	}
	
	public static boolean isRandom() {
		return randomization;
	}
	
}
