package phoebe.game;

import phoebe.Log;
import phoebe.UserInput;
import phoebe.basic.Vector;

/* Az általános foltokat megvalósító absztarkt osztály. Tárolja a folt helyét,
 * hátralévõ élettartamát, és megvalósítja a foltok általános metódusait
 */
public abstract class Smudge {
	
	protected Vector position;
	protected int duration;
	protected int thickness;
	
	
	/** 
	 * Konstruktor a folt pozíciójának megadásával
	 * @param p a folt pozíciója
	 */
	public Smudge(Vector p) {		
		position = p;
		duration = 3;
	}
	
	
	/** 
	 * Metódus annak eldöntésére, hogy a folt egy adott helyen fejt-e ki hatást
	 * @param p a hely, ahol vizsgáljuk a folt hatásoságát
	 * @return logikai érték, mely megadja, hogy a vizsgált helyen fejt-e ki hatást a folt
	 */
	public boolean isEffectiveAt(Vector p) {
	
		
		
		return false;
	}
	
	/** 
	 * A folt vastagságát cökkentõ metódus
	 * @return a folt vastagsága
	 */
	public int reduceThickness(){
		return thickness--;
	}
	
	/** 
	 * A folt vastagságát lekérdezõ metódus
	 * @return a folt vastagsága
	 */
	public int getThickness(){
		return thickness;
	}
	
	
	/** 
	 * A folt pozícióját lekérdezõ metódus
	 * @return a folt pozíciója
	 */
	public Vector getPosition(){
		return position;
	}
	
	
	/** 
	 * Absztrakt metódus, mely a folt típusától függõ változást hoz létre az átadott robotban
	 * @param r a robot, amin változtatást kell létrehozni
	 */
	public abstract void action(PlayerRobot r);	
	

	/** 
	 * Absztark metódus, ami folt új körbe lépését megvalósítja meg
	 * @return a folt kora
	 */
	public abstract int nextRound();
	
	
	public abstract String toString();
	
}
