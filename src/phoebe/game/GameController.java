package phoebe.game;

import phoebe.Log;
import phoebe.UserInput;

/** 
 * A játék létrehozásáért és tárolásáért felelõs osztály
 */
public class GameController {
	
	private Game game;
	private boolean running;

	/** 
	 * Létrehoz egy új játékot ha nincs épp futás alatt egy másik, és elmenti hogy fut
	 */
	public void newGame() {
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(GameController.class, "newGame");
		
		// Ha nem fut épp játék, létrehoz egy újat
		// Szkeleton megvalósítás miatt meghívja az isRunningot, mert ott teszi fel a kérdést
		if (!isRunning()) {
			Map map = new Map("map.xml");
			
			// Szkeleton megvalósítás, bekérjük a játékosok számát
			int players = UserInput.getInt("Hány játékossal?", 5);
			
			if (players < 2) { players = 2; }
			else if (players > 5) { players = 5; }
			game = new Game(players, map, this);
			running = true;
		}
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}
	
	/**
	 * Beállítja, hogy nincs futó játék
	 */
	public void gameEnded() {
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(GameController.class, "gameEnded");
		
		running = false;
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}
	
	/**
	 * Visszaadja hogy van-e éppen futó játék
	 * @return
	 */
	public boolean isRunning() {
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(GameController.class, "isRunning");
		
		running = UserInput.getBoolean("Van futó játék?", false);
		
		//Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(String.valueOf(running));		
		return running;
	}
	
}
