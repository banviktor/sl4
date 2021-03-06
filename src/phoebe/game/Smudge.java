package phoebe.game;

import phoebe.basic.Vector;

/** 
 * Az általános foltokat megvalósító absztarkt osztály. Tárolja a folt helyét,
 * hátralévő élettartamát, és megvalósítja a foltok általános metódusait
 */
public abstract class Smudge {	
	protected Vector position;
	protected int duration;
	protected int thickness;
	protected double radius = 0.5;
	
	
	/** 
	 * Konstruktor a folt pozíciójának megadásával
	 * @param p a folt pozíciója
	 */
	public Smudge(Vector p) {		
		position = p;
		duration = 6;
		thickness = 2;
	}
	
	
	/** 
	 * Metódus annak eldöntésére, hogy az egységnyi sugarú folt egy adott helyen fejt-e ki hatást
	 * @param p a hely, ahol vizsgáljuk a folt hatásoságát
	 * @return logikai érték, mely megadja, hogy a vizsgált helyen fejt-e ki hatást a folt
	 */
	public boolean isEffectiveAt(Vector p) {
		return (position.distance(p) <= radius);
	}
	
	/** 
	 * A folt vastagságát cökkentő metódus
	 * @return a folt vastagsága
	 */
	public int reduceThickness(){
		return --thickness;
	}
	
	/** 
	 * A folt vastagságát lekérdező metódus
	 * @return a folt vastagsága
	 */
	public int getThickness(){
		return thickness;
	}
	
	
	/** 
	 * A folt élettartamát lekérdező metódus
	 * @return a folt élettartama
	 */
	public int getDuration(){
		return duration;
	}
	
	
	/** 
	 * A folt pozícióját lekérdező metódus
	 * @return a folt pozíciója
	 */
	public Vector getPosition(){
		return position;
	}
	
	
	/** 
	 * Absztrakt metódus, mely a folt típusától függő változást hoz létre az átadott robotban
	 * @param r a robot, amin változtatást kell létrehozni
	 */
	public abstract int action(PlayerRobot r);	
	

	/** 
	 * Absztrakt metódus, ami folt új körbe lépését megvalósítja meg
	 * @return a folt élettartama
	 */
	public abstract int nextRound();
	
	/**
	 * Visszatér a folt sugarával.
	 * @return folt sugara
	 */
	public double getRadius() {
		return radius;
	}
	
	
	public abstract String toString();
	
}