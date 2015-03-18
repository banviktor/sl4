package phoebe.game;

import java.util.List;

import phoebe.Application;
import phoebe.Log;
import phoebe.UserInput;

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
	private List<Robot> robots;
	
	
	/**
	 * Konstruktor
	 * @param n a k�r�k sz�ma a j�t�kban
	 * @param m a p�lya, amin a j�t�k j�tsz�dik
	 * @param gc a j�t�khoz tartoz� gameController
	 */
	public Game(int n, Map m, GameController gc){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t.
		Log.enterFunction(Game.class, "Game", String.valueOf(n) + "Map" + "GameController");

		playerNumber = n;
		map = m;
		gameController = gc;
		
		//F�ggv�nyb�l kil�p�s ki�r�sa
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
		
		//F�ggv�nyb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(robots.get(actualRobotNumber).toString());
		return null;
	}
	
	/**
	 * T�rli az aktu�lis robotot
	 */
	public void deleteActualRobot(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Game.class, "deleteActualRobot");
		
		//F�ggv�nyb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * A j�t�k v�get�r�s�t megval�s�t� met�dus, kiv�lasztja a nyertest �s le�ll�tja a j�t�kot
	 */
	public void gameEnd(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Game.class, "gameEnd");
		
		for (Robot i : robots) {
			i.getDistance();
		}
		
		//F�ggv�nyb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * A j�t�kban l�v� robotok lek�rdez�s�t szolg�l� met�dus
	 * @return a j�t�kban l�v�robotok
	 */
	public List<Robot> getRobots(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Game.class, "getRobots");
		
		//F�ggv�nyb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction("List<Robots>");
		return null;
	}
	
}
