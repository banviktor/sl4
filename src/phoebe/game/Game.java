package phoebe.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import phoebe.Application;
import phoebe.Log;
import phoebe.UserInput;
import phoebe.basic.Color;
import phoebe.basic.Vector;

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
	private GameController robotController;
	private List<Robot> robots;
	
	
	/**
	 * Konstruktor
	 * @param n a körök száma a játékban
	 * @param m a pálya, amin a játék játszódik
	 * @param gc a játékhoz tartozó gameController
	 */
	private Vector startingVector() {
		Vector v = null;
		Random r = new Random();
		do {
			v = new Vector( r.nextDouble(), r.nextDouble() );
		} while ( !map.isOnRoad(v) );
		return v;
	}
	
	public Game(int n, Map m, GameController gc){
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát.
		Log.enterFunction(Game.class, "Game", String.valueOf(n) + "Map" + "GameController");

		playerNumber = n;
		map = m;
		gameController = gc;
		actualRobotNumber = 1;
		robots = new ArrayList<Robot>();
		for (int i=0; i<playerNumber; ++i) {
			robots.add( new Robot( Color.values()[i], startingVector() ) );
		}
		
		RobotController robotController = new RobotController(robots.get(actualRobotNumber), this, map);
		
		//Metódusból kilépés kiírása
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
		
		//Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(robots.get(actualRobotNumber).toString());
		return null;
	}
	
	/**
	 * Törli az aktuális robotot
	 */
	public void deleteActualRobot(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Game.class, "deleteActualRobot");
		
		// Kitörli a robotot a listából 
		robots.remove( actualRobotNumber );
		// Visszalép, hogy a következõ robot helyes legyen
		actualRobotNumber--;
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}
	
	/**
	 * A játék végetérését megvalósító metódus, kiválasztja a nyertest és leállítja a játékot
	 */
	public void gameEnd(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Game.class, "gameEnd");
		
		Robot winner = robots.get(0);
		for (Robot i : robots) {
			if ( i.getDistance() > winner.getDistance() ) {
				winner = i;
			}
		}
		Log.writeLine("The winner is Robot " + winner.getColor().toString() + "!!" );
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}
	
	/**
	 * A játékban lévõ robotok lekérdezését szolgáló metódus
	 * @return a játékban lévõrobotok
	 */
	public List<Robot> getRobots(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Game.class, "getRobots");
		
		//Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction("List<Robots>");
		return robots;
	}
	
}
