package phoebe.game;

import phoebe.basic.Vector;

/* Az általános foltokat megvalósító absztarkt osztály. Tárolja a folt helyét,
 * hátralévõ élettartamát, és megvalósítja a foltok általános metódusait
 */
public abstract class Smudge {
	
	protected Vector position;
	protected int remainingRounds;

	
	/** 
	 * Konstruktor a folt pozíciójának megadásával
	 * @param p a folt pozíciója
	 */
	public Smudge(Vector p){}
	
	
	/** 
	 * Metódus annak eldöntésére, hogy a folt egy adott helyen fejt-e ki hatást
	 * @param p a hely, ahol vizsgáljuk a folt hatásoságát
	 * @return logikai érték, mely megadja, hogy a vizsgált helyen fejt-e ki hatást a folt
	 */
	public boolean isEffectiveAt(Vector p){ return false; }
	
	
	/** 
	 * A folt öregítését megvalósító metódus
	 * @return a folt új kora
	 */
	public int makeOlder(){ return 0; }
	
	
	/** 
	 * A folt pozícióját lekérdezõ metódus
	 * @return a folt pozíciója
	 */
	public Vector getPosition(){ return null; }
	
	
	/** 
	 * Absztrakt metódus, mely a folt típusától függõ változást hoz létre az átadott robotban
	 * @param r a robot, amin változtatást kell létrehozni
	 */
	public abstract void action(Robot r);	
	
}
