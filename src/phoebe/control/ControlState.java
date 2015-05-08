package phoebe.control;

public class ControlState {
	private static boolean enabled = true;
	
	/**
	 * Elengedi a vezérlést.
	 */
	public synchronized static void release(){
		enabled = true;
	}
	
	/**
	 * Lefoglalja a vezérlést. Ha nem sikerült, false-t ad vissza.
	 * @return sikeres foglalás
	 */
	public synchronized static boolean get(){
		if(enabled){
			enabled = false;
			return true;
		}
		return false;
	}
}
