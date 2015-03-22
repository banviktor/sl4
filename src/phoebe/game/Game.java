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
 * A j�t�kot megval�s�t� oszt�ly, mely t�rolja a robotokat �s �temezi a k�r�ket,
 * �szleli a j�t�k v�g�t �s meg�llap�tja a nyertest
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
	 * Seg�df�ggv�ny, amely gener�l egy random vektort a robot kezdeti hely�nek
	 * @return a gener�lt v�letlenszer� vektor
	 */
	private Vector startingVector() {
//Logol�s?		
		Vector v = null;
		Random r = new Random();
		
		do {
			v = new Vector( r.nextDouble(), r.nextDouble() );
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
		robots = new ArrayList<Robot>();
		// A megfelel� sz�m� robot l�trehoz�sa v�letlen poz�ci�ra
		for (int i=0; i<playerNumber; ++i) {
			robots.add( new Robot( Color.values()[i], startingVector() ) );
		}
		
		robotController = new RobotController(robots.get(actualRobotNumber), this, map);
		
		//Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * A sorrendben k�vetkez� robotot adja vissza, �s megjegyzi hogy 
	 * az �pp �tadott az aktu�lis robot
	 * @return a sorrendben k�vetkez� robot
	 */
	public Robot getNextRobot(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Game.class, "getNextRobot");	
		
		//Mivel nincs m�k�d� isRunning, ez�rt kelleni fog egy boolean arra az esetre
		//ha v�get �r a j�t�k
		boolean ended = false;
		
		// Megk�rdezz�k a felhaszn�l�t, hogy v�get �rt-e egy k�r
		if (UserInput.getBoolean("V�get �rt egy k�r?", false)) {
			
			// Ha k�r v�ge van megk�rdezz�k a felhaszn�l�t, hogy j�t�k v�ge is-e egyben
			if(UserInput.getBoolean("V�get �rt a j�t�k?", false)) {
			
				// Ha v�ge a j�t�knak, megh�vjuk a hozz� tartoz� met�dust
				gameEnd();	
				ended = true;
				
			} else {
				
				// Ha nem �rt v�get a j�t�k de �j k�r van, akkor ezt jelezz�k a Mapnek
				map.nextRound();
				
			}
			
			// Ha �j k�r van vagy a j�t�k v�get �rt, az els� robot lesz az aktu�lis
			actualRobotNumber = 1;	
			
		} else {		
			// Ha nincs �j k�r a k�vetkez� robotot adjuk vissza
			// Szkeleton megval�s�t�s: itt most szint�n az els� robotot adjuk vissza, mert nem tudhatjuk
			// mennyi van, �s a userinput miatt nem tesztelj�k m�g hogy v�ge-e egy k�rnek
			actualRobotNumber = 1;
		}
		
		//Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(robots.get(actualRobotNumber).toString());
		if(ended)
			return null;
		return robots.get(actualRobotNumber);
	}
	
	/**
	 * T�rli az aktu�lis robotot
	 */
	public void deleteActualRobot(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Game.class, "deleteActualRobot");
	
		//Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * A j�t�k v�get�r�s�t megval�s�t� met�dus, kiv�lasztja a nyertest �s le�ll�tja a j�t�kot
	 */
	public void gameEnd(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
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

		//Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * A j�t�kban l�v� robotok lek�rdez�s�t szolg�l� met�dus
	 * @return a j�t�kban l�v�robotok
	 */
	public List<Robot> getRobots(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Game.class, "getRobots");
		
		//Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction("List<Robots>");
		return robots;
	}
	
	/**
	 * Szkeleton megval�s�t�s: csak itt lesz sz�ks�gi ilyen lek�rdez�sre
	 * Visszaadja a j�t�khoz tartoz� RobotControllert
	 * @return a RobotController
	 */
	public RobotController getRobotController(){
		return robotController;
	}	
	
	/**
	 * Szkeleton megval�s�t�s: csak itt lesz sz�ks�gi ilyen lek�rdez�sre
	 * Visszaadja a j�t�khoz tartoz� Mapet
	 * @return a Map
	 */
	public Map getMap(){
		return map;
	}	
	
}
