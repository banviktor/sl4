package phoebe.game;

import phoebe.Log;
import phoebe.UserInput;
import phoebe.basic.Vector;

public class Oil extends Smudge {
	
	/**
	 * Konstruktor az olajfolt pozíciójának megadásával
	 * @param p pozíció
	 */
	public Oil(Vector p) {
		super(p);
		
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Glue.class, "Glue", p.toString());
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}

	
	/** 
	 * Metódus annak eldöntésére, hogy az olajfolt egy adott helyen fejt-e ki hatást
	 * Csak a szkeletonba kell felülírni a szülõ függvényét, itt a kérdés feltétele miatt
	 * @param p a hely, ahol vizsgáljuk a folt hatásoságát
	 * @return logikai érték, mely megadja, hogy a vizsgált helyen fejt-e ki hatást a folt
	 */
	@Override
	public boolean isEffectiveAt(Vector p) {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Oil.class, "isEffectiveAt", p.toString());
				
		//Megadjuk, hogy a folt alatta van-e
		boolean isHere = UserInput.getBoolean("Ez az olajfolt a robot alatt van?", false);
		
		//Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(String.valueOf(isHere));
		return isHere;
	}
	
	
	/**
	 * A folt hatását valósítja meg, az átadott robotra kikapcsolja a sebességváltoztatás
	 * lehetõségét
	 * @param r a robot, aminek kikapcsolja a sebességváltoztatását
	 */
	@Override
	public void action(Robot r){
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Oil.class, "action", r.toString());
		
		r.disableSpeedModification();
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}


	@Override
	public String toString() {
		return "Oil@" + position;
	}
		
}
