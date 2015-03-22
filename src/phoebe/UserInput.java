package phoebe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {
	
	private static boolean asking = false;
	
	private static void ask(String question){
		Log.write("[?] " + question, false);
	}
	
	public static boolean getBoolean(String question, boolean defaultValue){
		if(!asking)
			return defaultValue;
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		while(true){
			ask(question + " (I/N) :");
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
		if(!asking)
			return defaultValue;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		while(true){
			ask(question + " (#) :");
			try{
	            int i = Integer.parseInt(br.readLine());
	            return i;
	        } catch(NumberFormatException nfe){
	            
	        } catch (IOException e) {
	        	
			}
		}
	}
	
	public static String[] getCommand(){
		System.out.print(" >");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		try{
            String i = br.readLine();
            return i.split(" ");
        } catch (IOException e) {
        	return new String[]{};
		} 	
	}
	
	public static void enable(){
		asking = true;
	}
	
	public static void disable(){
		asking = false;
	}
	
}
