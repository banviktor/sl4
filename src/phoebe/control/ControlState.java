package phoebe.control;

public class ControlState {
	private static boolean enabled = true;
	
	public synchronized static void release(){
		enabled = true;
	}
	
	public synchronized static boolean get(){
		if(enabled){
			enabled = false;
			return true;
		}
		return false;
	}
}
