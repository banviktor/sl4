package phoebe;

public class UserInput {
	
	private static boolean asking = false;
	
	private static void ask(String question){
		Log.write("[?] " + question, false);
	}
	
	public static boolean getBoolean(String question, boolean defaultValue){
		if(!asking)
			return defaultValue;
		ask(question + " (I/N) :");
			return false;
	}
	
	public static int getInt(String question, int defaultValue){
		if(!asking)
			return defaultValue;
		ask(question + " (#) :");
			return 0;
	}
	
	public static void setAsking(boolean value){
		asking = value;
	}
	
}
