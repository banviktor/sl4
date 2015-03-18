package phoebe.game;

import phoebe.Log;
import phoebe.UserInput;
import phoebe.basic.Vector;

public class Glue extends Smudge {
	
	/**
	 * Konstruktor az olajfolt pozíciójának megadásával
	 * @param p pozíció
	 */
	public Glue(Vector p) {
		super(p);
		
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Glue.class, "Glue", p.toString());
		
		// Itt hívjuk meg a Smudge konstruktorhíváshoz tartozó kiírást is, mivel a super(p)
		// metódushívásnak kell az elsõnek lennie ebben a konstruktornak, és csak így tudjuk
		// megvalósítani a kiírások helyes sorrendjét
		Log.enterFunction(Smudge.class, "Smudge", p.toString());

		//Smudge konstruktorából való kilépés kiírása
		Log.exitFunction();
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}

	
	/** 
	 * Metódus annak eldöntésére, hogy a ragacsfolt egy adott helyen fejt-e ki hatást
	 * Csak a szkeletonba kell felülírni a szülõ függvényét, itt a kérdés feltétele miatt
	 * @param p a hely, ahol vizsgáljuk a folt hatásoságát
	 * @return logikai érték, mely megadja, hogy a vizsgált helyen fejt-e ki hatást a folt
	 */
	@Override
	public boolean isEffectiveAt(Vector p) {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Glue.class, "isEffectiveAt", p.toString());
				
		//Megadjuk, hogy a folt alatta van-e
		boolean isHere = UserInput.getBoolean("Ez a ragacsfolt a robot alatt van?", false);
		
		//Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(String.valueOf(isHere));
		return isHere;
	}
	
	
	/**
	 * A folt hatását valósítja meg, az átadott robotnak felezi a sebességét
	 * @param r a robot, aminek felezi a sebességét
	 */
	@Override
	public void action(Robot r) {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Glue.class, "action", r.toString());
		
		r.halveSpeed();
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}


	@Override
	public String toString() {
		return "Glue@" + position;
	}	

}
