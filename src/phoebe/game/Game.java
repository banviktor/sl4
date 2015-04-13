package phoebe.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	private List<PlayerRobot> playerRobots;
	private List<CleaningRobot> cleaningRobots;
	
	
	/**
	 * Visszaad egy lehetséges kezdõpozíciót, ami rajta van a pályán.
	 * @return Kezdõpozíció
	 */
	private Vector startingVector() {
		Vector v = null;
		Random r = new Random();
		int tries = 0;
		do {
			v = new Vector( r.nextDouble()*10, r.nextDouble()*10 );
			v = UserInput.getVector(tries==0?"Kezdõ pozíció":"Újra", v);
			++tries;
		} while ( !map.isOnRoad(v) );
		return v;
	}
	
	/**
	 * Konstruktor
	 * @param n a körök száma a játékban
	 * @param m a pálya, amin a játék játszódik
	 * @param gc a játékhoz tartozó gameController
	 */
	public Game(int n, Map m, GameController gc){
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát.
		Log.enterFunction(Game.class, "Game", String.valueOf(n) + ", Map" + ", GameController");

		playerNumber = n;
		map = m;
		gameController = gc;
		actualRobotNumber = 1;
		playerRobots = new ArrayList<PlayerRobot>();
		cleaningRobots = new ArrayList<CleaningRobot>();
		for (int i=0; i<playerNumber; ++i) {
			playerRobots.add( new PlayerRobot( Color.values()[i], startingVector()));
		}
		
		RobotController robotController = new RobotController(playerRobots.get(actualRobotNumber), this, map);
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}
	
	/**
	 * A sorrendben következõ robotot adja vissza, és megjegyzi hogy 
	 * az épp átadott az aktuális robot
	 * @return a sorrendben következõ robot
	 */
	public PlayerRobot getNextPlayerRobot(){
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
		Log.exitFunction(playerRobots.get(actualRobotNumber).toString());
		return null;
	}
	
	/**
	 * Törli az aktuális robotot
	 */
	public void deleteActualRobot(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Game.class, "deleteActualRobot");
		
		// Kitörli a robotot a listából 
		playerRobots.remove( actualRobotNumber );
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
		
		PlayerRobot winner = playerRobots.get(0);
		for (PlayerRobot i : playerRobots) {
			if ( i.getDistance() > winner.getDistance() ) {
				winner = i;
			}
		}
		Log.writeLine("The winner is Robot " + winner.getColor().toString() + "!!" );
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}
	
	/**
	 * A játékban lévõ játékosrobotok lekérdezését szolgáló metódus
	 * @return a játékban lévõ játékosrobotok
	 */
	public List<PlayerRobot> getPlayerRobots(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Game.class, "getRobots");
		
		//Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction("List<Robots>");
		return playerRobots;
	}
	
	/**
	 * A játékban lévõ takarítórobotok lekérdezését szolgáló metódus
	 * @return a játékban lévõ takarítórobotok
	 */
	public List<CleaningRobot> getCleaningRobots() {
		return cleaningRobots;
	}
	
	public Map getMap() {
		return map;
	}
	
	public GameController getGameController() {
		return gameController;
	}
	
}
