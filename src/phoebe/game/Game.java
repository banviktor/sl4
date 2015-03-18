package phoebe.game;

import java.util.List;

import phoebe.Application;
import phoebe.Log;
import phoebe.UserInput;

/**
 * A játékot megvalósító osztály, mely tárolja a robotokat és ütemezi a köröket,
 * észleli a játék végét és megállapítja a nyertest
 */
public class Game {
	
	private int playerNumber;
	private int actualRobotNumber;
	private int turnsRemaining;
	private Map map;
	private GameController gameController;
	private List<Robot> robots;
	
	
	/**
	 * Konstruktor
	 * @param n a körök száma a játékban
	 * @param m a pálya, amin a játék játszódik
	 * @param gc a játékhoz tartozó gameController
	 */
	public Game(int n, Map m, GameController gc){
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát.
		Log.enterFunction(Game.class, "Game", String.valueOf(n) + "Map" + "GameController");

		playerNumber = n;
		map = m;
		gameController = gc;
		
		//Függvénybõl kilépés kiírása
		Log.exitFunction();
	}
	
	/**
	 * A sorrendben következõ robotot adja vissza, és megjegyzi hogy 
	 * az épp átadott az aktuális robot
	 * @return a sorrendben következõ robot
	 */
	public Robot getNextRobot(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Game.class, "getNextRobot");	
		
		// Megkérdezzük a felhasználót, hogy véget ért-e egy kör
		if (UserInput.getBoolean("Véget ért egy kör?", false)) {
			
			// Ha kör vége van megkérdezzük a felhasználót, hogy játék vége is-e egyben
			if(UserInput.getBoolean("Véget ért a játék?", false)) {
			
				// Ha vége a játéknak, meghívjuk a hozzá tartozó metódust
				gameEnd();
				
			} else {
				
				// Ha nem ért véget a játék de új kör van, akkor ezt jelezzük a Mapnek
				map.nextRound();
				
			}
		}		
		
		//Függvénybõl kilépés kiírása a visszatérési értékkel
		Log.exitFunction(robots.get(actualRobotNumber).toString());
		return null;
	}
	
	/**
	 * Törli az aktuális robotot
	 */
	public void deleteActualRobot(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Game.class, "deleteActualRobot");
		
		//Függvénybõl kilépés kiírása
		Log.exitFunction();
	}
	
	/**
	 * A játék végetérését megvalósító metódus, kiválasztja a nyertest és leállítja a játékot
	 */
	public void gameEnd(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Game.class, "gameEnd");
		
		for (Robot i : robots) {
			i.getDistance();
		}
		
		//Függvénybõl kilépés kiírása
		Log.exitFunction();
	}
	
	/**
	 * A játékban lévõ robotok lekérdezését szolgáló metódus
	 * @return a játékban lévõrobotok
	 */
	public List<Robot> getRobots(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Game.class, "getRobots");
		
		//Függvénybõl kilépés kiírása a visszatérési értékkel
		Log.exitFunction("List<Robots>");
		return null;
	}
	
}
