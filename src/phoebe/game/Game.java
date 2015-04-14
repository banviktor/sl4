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
	private RobotController robotController;
	private List<PlayerRobot> playerRobots;
	private List<CleanerRobot> cleanerRobots;
	
	
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
		actualRobotNumber = 0;
		playerRobots = new ArrayList<PlayerRobot>();
		cleanerRobots = new ArrayList<CleanerRobot>();
		for (int i=0; i<playerNumber; ++i) {
			playerRobots.add( new PlayerRobot( Color.values()[i], startingVector()));
		}
		
		robotController = new RobotController(playerRobots.get(actualRobotNumber), this, map);
		
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
	
	
	
	
	private void spawnCleanerRobot() {
		
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
	 * Megsemmisíti az adott pozícion lévõ takarítórobotokat
	 * @param p az adott pozíció vektora
	 */
	public void deleteCleanerRobotsAt(Vector p){
		for(CleanerRobot cr : cleanerRobots){
			if(cr.isAt(p)){
				
				//Olajfolt létrehozása
				cr.createOil();
				
				//takarítórobot törlése
				cleanerRobots.remove(cr);
			}
		}
	}
	
	
	
	public void collidePlayerRobotsWithActual() {
		for(PlayerRobot pr : playerRobots){
			if(pr.isAt(playerRobots.get(actualRobotNumber).getPosition())){
				if(pr.getSpeedVector().length() > playerRobots
						.get(actualRobotNumber).getSpeedVector().length()){
					
					//Amennyiben a másik robot a gyorsabb az épp ugró robot összetörik
					deleteActualRobot();
					//Véget ér az iteráció
					return;
					
				} else if(pr.getSpeedVector().length() == playerRobots
						.get(actualRobotNumber).getSpeedVector().length()){
					
					//Amennyiben az aktuális robot a gyorsabb a másik robot törik össze
					playerRobots.remove(pr);
					
					/*Két robot nem lehet egymáson, mivel legaláb az egyik összetörik
					 *amennyiben egymásra ugranak, így nincs értelme tovább iterálni
					 */
					return;
					
				} else if(pr.getSpeedVector().length() < playerRobots
						.get(actualRobotNumber).getSpeedVector().length()){
					
					//Ha egyforma gyorsak mindeketten összetörnek
					deleteActualRobot();
					playerRobots.remove(pr);
					
					//Az iteráció véget ér
					return;
					
				}
			}
		}
	}
	
	
	
	public boolean isRobotAt() {
		return false;
	}
	
	
	/**
	 * A játék végetérését megvalósító metódus, kiválasztja a nyertest és leállítja a játékot
	 */
	private void gameEnd(){
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
		return playerRobots;
	}
	
	
	/**
	 * A játékban lévõ takarítórobotok lekérdezését szolgáló metódus
	 * @return a játékban lévõ takarítórobotok
	 */
	public List<CleanerRobot> getCleaningRobots() {
		return cleanerRobots;
	}
	
	
	/**
	 * A játékban lévõ robotok lekérdezését szolgáló metódus
	 * @return a játékban lévõ robotok
	 */
	public List<Robot> getRobots() {
		// Létrehozunk egy listát a játékosrobotokkal
		List<Robot> robots = new ArrayList<Robot>(playerRobots);
		
		// Hozzáadjuk a listához a ttakaítórobotokat is
		for(CleanerRobot cr : cleanerRobots) {
			robots.add(cr);
		}
		
		return robots;
	}
	
	
	/**
	 * Visszaadja az itt tárolt pályát
	 * @return Pálya
	 */
	public Map getMap() {
		return map;
	}
	
	
	/**
	 * Visszaadja az itt tárolt robotvezérlõt
	 * @return Robotvezérlõ
	 */
	public RobotController getRobotController() {
		return robotController;
	}
	
}
