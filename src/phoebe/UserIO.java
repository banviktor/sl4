package phoebe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import phoebe.basic.Vector;

public class UserIO {
	
	private static boolean randomization = false;
	private static boolean questions = true;
	
	private static void ask(String question, boolean newline){
		if (newline)
			question += "\n";
		if (questions)
			System.out.print(question);
	}
	
	private static void ask(String question){
		if (questions)
			System.out.print(question);
	}
	
	public static boolean getBoolean(String question, boolean defaultValue){
		if(randomization)
			return defaultValue;
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
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
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
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
		ask("> "); //TODO: teszthez kiszedni
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		try{
            String i = br.readLine().toLowerCase();
            return i.split(" ");
        } catch (IOException e) {
        	return new String[]{};
		} 	
	}
	
	public static void randomOn(){
		System.out.println("Véletlenszerű események bekapcsolva.");
		randomization = true;
	}
	
	public static void randomOff(){
		System.out.println("Véletlenszerű események kikapcsolva.");
		randomization = false;
	}
	
	public static void questionsOn(){
		System.out.println("Kérdezés bekapcsolva.");
		questions = true;
	}
	
	public static void questionsOff(){
		System.out.println("Kérdezés kikapcsolva.");
		questions = false;
	}
	
	public static boolean getRandom() {
		return randomization;
	}
	
}
