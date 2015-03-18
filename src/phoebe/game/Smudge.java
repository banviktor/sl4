package phoebe.game;

import phoebe.Log;
import phoebe.UserInput;
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
	public Smudge(Vector p) {
		// Ezt a függvényhívást nem logoljuk, mivel csak egy Glue vagy egy Oil konstruktorában
		// hívható meg super(p) hívással (mivel az absztrakt osztály nem példányosítható),
		// azok viszont már kezelik ezt a hívást
	}
	
	
	/** 
	 * Metódus annak eldöntésére, hogy a folt egy adott helyen fejt-e ki hatást
	 * @param p a hely, ahol vizsgáljuk a folt hatásoságát
	 * @return logikai érték, mely megadja, hogy a vizsgált helyen fejt-e ki hatást a folt
	 */
	public boolean isEffectiveAt(Vector p) {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Smudge.class, "isEffectiveHere", p.toString());
				
		//Megadjuk, hogy a folt alatta van-e
		boolean isHere = UserInput.getBoolean("Ez a folt a robot alatt van?", false);
		
		//Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(String.valueOf(isHere));
		return isHere;
	}
	
	
	/** 
	 * A folt öregítését megvalósító metódus
	 * @return a folt új kora
	 */
	public int makeOlder() {
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Smudge.class, "makeOlder");
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		// Szkeletonban mindig 1-el tér vissza, mert itt egy érték megkérdezése a felhasználótól
		// felesleges, nem okozna új metódushívást semmilyen lehetséges érték
		Log.exitFunction("1");
		return 1;
	}
	
	
	/** 
	 * A folt pozícióját lekérdezõ metódus
	 * @return a folt pozíciója
	 */
	public Vector getPosition(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Smudge.class, "getPosition");
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(position.toString());
		return position;
	}
	
	/** 
	 * Absztrakt metódus, mely a folt típusától függõ változást hoz létre az átadott robotban
	 * @param r a robot, amin változtatást kell létrehozni
	 */
	public abstract void action(Robot r);	
	
	public abstract String toString();
	
}
