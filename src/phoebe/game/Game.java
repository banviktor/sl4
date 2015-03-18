package phoebe.game;

import java.util.List;

import phoebe.Application;
import phoebe.Log;

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
		
		Log.exitFunction();
	}
	
	/**
	 * A sorrendben következõ robotot adja vissza, és megjegyzi hogy 
	 * az épp átadott az aktuális robot
	 * @return a sorrendben következõ robot
	 */
	public Robot getNextRobot(){
		Log.enterFunction(Game.class, "getNextRobot");
	//////////////////////	
		
		
		Log.exitFunction(robots.get(actualRobotNumber).toString());
		return null;
	}
	
	/**
	 * Törli az aktuális robotot
	 */
	public void deleteActualRobot(){
		Log.enterFunction(Game.class, "deleteActualRobot");
		
		Log.exitFunction();
	}
	
	/**
	 * A játék végetérését megvalósító metódus, kiválasztja a nyertest és leállítja a játékot
	 */
	public void gameEnd(){
		Log.enterFunction(Game.class, "gameEnd");
		
		for (Robot i : robots) {
			i.getDistance();
		}
		
		Log.exitFunction();
	}
	
	/**
	 * A játékban lévõ robotok lekérdezését szolgáló metódus
	 * @return a játékban lévõrobotok
	 */
	public List<Robot> getRobots(){
		Log.enterFunction(Game.class, "getRobots");
				
		Log.exitFunction("List<Robots>");
		return null;
	}
	
}
