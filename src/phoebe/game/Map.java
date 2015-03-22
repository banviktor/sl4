package phoebe.game;

import java.util.ArrayList;
import java.util.List;

import phoebe.Log;
import phoebe.UserInput;
import phoebe.basic.Line;
import phoebe.basic.Vector;

public class Map {

	private List<Line> lines;
	private double lineWidth;
	private int rounds;
	private List<Smudge> smudges;

	/**
	 * Beolvassa és feldolgozza a térképfájlt.
	 * @param map a map fájl helye
	 */
	public Map(String map) {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "Map", map);
		
		lineWidth = 0;
		rounds = 3;
		smudges = new ArrayList<Smudge>();
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}

	/**
	 * Beteszi a foltot a smudges listába.
	 * @param s az új folt
	 */
	public void addSmudge(Smudge s) {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "addSmudge", s.toString());
		
		smudges.add(s);
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}

	/**
	 * Minden foltot egyel öregít. Azokat a foltokat, amelyeknek 0 lesz az
	 * élettartama, törli a smudges listából.
	 */
	public void nextRound() {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "nextRound");
					
		for (Smudge s : smudges) {
			UserInput.getInt("A " + s + " hátralevõ köreinek száma nulla?", 3);
		}
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}

	/**
	 * Visszaadja az összes foltot.
	 * @return foltok
	 */
	public List<Smudge> getSmudges() {
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Map.class, "getSmudges");
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction("List<Smudge>");
				
		return smudges;
	}

	/**
	 * Visszaadja az adott pontban hatást kifejtõ foltokat
	 * @param v pont
	 * @return itt ható foltok
	 */
	public List<Smudge> getSmudgesAt(Vector v) {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "getSmudgesAt", v.toString());
				
		List<Smudge> smudgesAt = new ArrayList<Smudge>();
		for (Smudge s : smudges) {
			if ( s.isEffectiveAt( v )) {
				smudgesAt.add( s );
			}
		}
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction("List<Smudge>");
		
		return smudgesAt;
	}
	
	/**
	 * Visszaadja, hogy az adott pont rajta van-e a pályán.
	 * @param v Vizsgált pont
	 * @return igaz, ha az úton van
	 */
	public boolean isOnRoad(Vector v) {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "isOnRoad", v.toString());

		// Bekérjük a játékostól, hogy az adott pont a térképen van-e
		boolean result = UserInput.getBoolean("A térképen van ez a pont?", true);
		
		//Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(result);
				
		return result;
	}

	/**
	 * Visszaadja az utak listáját
	 * @return utak listája
	 */
	public List<Line> getLines() {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "getLines");
		
		//Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction("List<Line>");
				
		return lines;
	}

	/**
	 * Visszaadja az utak szélességét
	 * @return utak szélessége
	 */
	public double getLineWidth() {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "getLineWidth");
		
		//Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(lineWidth);
				
		return lineWidth;
	}

	/**
	 * Visszaadja a pályán játszható körök számát
	 * @return játszható körök száma
	 */
	public int getRounds() {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "getRounds");
		
		//Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(rounds);
		
		return rounds;
	}

}
