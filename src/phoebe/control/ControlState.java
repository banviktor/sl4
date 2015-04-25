package phoebe.control;

public class ControlState {
	private static boolean enabled = true;
	private static Object syncObject = new Object();
	
	public static void release(){
		synchronized (syncObject){
			enabled = true;
		}
	}
	
	public static boolean get(){
		synchronized (syncObject){
			if(enabled){
				enabled = false;
				return true;
			}
			return false;
		}
	}
}
