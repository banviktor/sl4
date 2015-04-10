package phoebe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {
	
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
	
	public static double[] getDoubles(String question, double[] defaultValue) {
		if(randomization)
			return defaultValue;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		ask(question, true);
		while(true){
			ask(question + " (#) (#) : ");
			try{
				double[] result = new double[2];
				for (int i = 0; i < result.length; i++) {
				    result[i] = Double.parseDouble(br.readLine().split(" ")[i]);
				}
	        } catch(NumberFormatException nfe){
	            
	        } catch (IOException e) {
	        	
			}
		}
	}
	
	public static String[] getCommand(){
		ask("> ");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		try{
            String i = br.readLine().toLowerCase();
            return i.split(" ");
        } catch (IOException e) {
        	return new String[]{};
		} 	
	}
	
	public static void randomOn(){
		randomization = true;
	}
	
	public static void randomOff(){
		randomization = false;
	}
	
	public static void questionsOn(){
		questions = true;
	}
	
	public static void questionsOff(){
		questions = false;
	}
	
}
