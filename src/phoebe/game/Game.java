package phoebe.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import phoebe.Log;
import phoebe.UserInput;
import phoebe.basic.Color;
import phoebe.basic.Vector;

/**
 * A j�t�kot megval�s�t� oszt�ly, mely t�rolja a robotokat �s �temezi a k�r�ket,
 * �szleli a j�t�k v�g�t �s meg�llap�tja a nyertest
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
	 * Visszaad egy lehets�ges kezd�poz�ci�t, ami rajta van a p�ly�n.
	 * @return Kezd�poz�ci�
	 */
	private Vector startingVector() {
		Vector v = null;
		Random r = new Random();
		int tries = 0;
		do {
			v = new Vector( r.nextDouble()*10, r.nextDouble()*10 );
			v = UserInput.getVector(tries==0?"Kezd� poz�ci�":"�jra", v);
			++tries;
		} while ( !map.isOnRoad(v) );
		return v;
	}
	
	/**
	 * Konstruktor
	 * @param n a k�r�k sz�ma a j�t�kban
	 * @param m a p�lya, amin a j�t�k j�tsz�dik
	 * @param gc a j�t�khoz tartoz� gameController
	 */
	public Game(int n, Map m, GameController gc){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t.
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
		
		//Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * A sorrendben k�vetkez� robotot adja vissza, �s megjegyzi hogy 
	 * az �pp �tadott az aktu�lis robot
	 * @return a sorrendben k�vetkez� robot
	 */
	public PlayerRobot getNextPlayerRobot(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Game.class, "getNextRobot");	
		
		// Megk�rdezz�k a felhaszn�l�t, hogy v�get �rt-e egy k�r
		if (UserInput.getBoolean("V�get �rt egy k�r?", false)) {
			
			// Ha k�r v�ge van megk�rdezz�k a felhaszn�l�t, hogy j�t�k v�ge is-e egyben
			if(UserInput.getBoolean("V�get �rt a j�t�k?", false)) {
			
				// Ha v�ge a j�t�knak, megh�vjuk a hozz� tartoz� met�dust
				gameEnd();
				
			} else {
				
				// Ha nem �rt v�get a j�t�k de �j k�r van, akkor ezt jelezz�k a Mapnek
				map.nextRound();
				
			}
		}		
		
		//Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(playerRobots.get(actualRobotNumber).toString());
		return null;
	}
	
	/**
	 * T�rli az aktu�lis robotot
	 */
	public void deleteActualRobot(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Game.class, "deleteActualRobot");
		
		// Kit�rli a robotot a list�b�l 
		playerRobots.remove( actualRobotNumber );
		// Visszal�p, hogy a k�vetkez� robot helyes legyen
		actualRobotNumber--;
		
		//Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * A j�t�k v�get�r�s�t megval�s�t� met�dus, kiv�lasztja a nyertest �s le�ll�tja a j�t�kot
	 */
	public void gameEnd(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Game.class, "gameEnd");
		
		PlayerRobot winner = playerRobots.get(0);
		for (PlayerRobot i : playerRobots) {
			if ( i.getDistance() > winner.getDistance() ) {
				winner = i;
			}
		}
		Log.writeLine("The winner is Robot " + winner.getColor().toString() + "!!" );
		
		//Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * A j�t�kban l�v� j�t�kosrobotok lek�rdez�s�t szolg�l� met�dus
	 * @return a j�t�kban l�v� j�t�kosrobotok
	 */
	public List<PlayerRobot> getPlayerRobots(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Game.class, "getRobots");
		
		//Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction("List<Robots>");
		return playerRobots;
	}
	
	/**
	 * A j�t�kban l�v� takar�t�robotok lek�rdez�s�t szolg�l� met�dus
	 * @return a j�t�kban l�v� takar�t�robotok
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
