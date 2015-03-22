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
	private RobotController robotController;
	private List<Robot> robots;
	
	/**
	 * Segédfüggvény, amely generál egy random vektort a robot kezdeti helyének
	 * @return a generált véletlenszerû vektor
	 */
	private Vector startingVector() {
//Logolás?		
		Vector v = null;
		Random r = new Random();
		
		do {
			v = new Vector( r.nextDouble(), r.nextDouble() );
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
		robots = new ArrayList<Robot>();
		// A megfelelõ számú robot létrehozása véletlen pozícióra
		for (int i=0; i<playerNumber; ++i) {
			robots.add( new Robot( Color.values()[i], startingVector() ) );
		}
		
		robotController = new RobotController(robots.get(actualRobotNumber), this, map);
		
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
		
		//Mivel nincs mûködõ isRunning, ezért kelleni fog egy boolean arra az esetre
		//ha véget ér a játék
		boolean ended = false;
		
		// Megkérdezzük a felhasználót, hogy véget ért-e egy kör
		if (UserInput.getBoolean("Véget ért egy kör?", false)) {
			
			// Ha kör vége van megkérdezzük a felhasználót, hogy játék vége is-e egyben
			if(UserInput.getBoolean("Véget ért a játék?", false)) {
			
				// Ha vége a játéknak, meghívjuk a hozzá tartozó metódust
				gameEnd();	
				ended = true;
				
			} else {
				
				// Ha nem ért véget a játék de új kör van, akkor ezt jelezzük a Mapnek
				map.nextRound();
				
			}
			
			// Ha új kör van vagy a játék véget ért, az elsõ robot lesz az aktuális
			actualRobotNumber = 1;	
			
		} else {		
			// Ha nincs új kör a következõ robotot adjuk vissza
			// Szkeleton megvalósítás: itt most szintén az elsõ robotot adjuk vissza, mert nem tudhatjuk
			// mennyi van, és a userinput miatt nem teszteljük még hogy vége-e egy körnek
			actualRobotNumber = 1;
		}
		
		//Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(robots.get(actualRobotNumber).toString());
		if(ended)
			return null;
		return robots.get(actualRobotNumber);
	}
	
	/**
	 * Törli az aktuális robotot
	 */
	public void deleteActualRobot(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Game.class, "deleteActualRobot");
	
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
		double winnerDistance = winner.getDistance();
		for (int i=1; i<robots.size(); ++i) {
			if ( robots.get(i).getDistance() > winnerDistance ) {
				winner = robots.get(i);
				winnerDistance = winner.getDistance();
			}
		}
		
		Log.writeLine("A nyertes: " + winner.toString() );
		
		gameController.gameEnded();

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
	
	/**
	 * Szkeleton megvalósítás: csak itt lesz szükségi ilyen lekérdezésre
	 * Visszaadja a játékhoz tartozó RobotControllert
	 * @return a RobotController
	 */
	public RobotController getRobotController(){
		return robotController;
	}	
	
	/**
	 * Szkeleton megvalósítás: csak itt lesz szükségi ilyen lekérdezésre
	 * Visszaadja a játékhoz tartozó Mapet
	 * @return a Map
	 */
	public Map getMap(){
		return map;
	}	
	
}
