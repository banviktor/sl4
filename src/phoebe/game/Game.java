package phoebe.game;

import java.util.List;

import phoebe.Application;
import phoebe.Log;

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
		
		Log.exitFunction();
	}
	
	/**
	 * A sorrendben k�vetkez� robotot adja vissza, �s megjegyzi hogy 
	 * az �pp �tadott az aktu�lis robot
	 * @return a sorrendben k�vetkez� robot
	 */
	public Robot getNextRobot(){
		Log.enterFunction(Game.class, "getNextRobot");
	//////////////////////	
		
		
		Log.exitFunction(robots.get(actualRobotNumber).toString());
		return null;
	}
	
	/**
	 * T�rli az aktu�lis robotot
	 */
	public void deleteActualRobot(){
		Log.enterFunction(Game.class, "deleteActualRobot");
		
		Log.exitFunction();
	}
	
	/**
	 * A j�t�k v�get�r�s�t megval�s�t� met�dus, kiv�lasztja a nyertest �s le�ll�tja a j�t�kot
	 */
	public void gameEnd(){
		Log.enterFunction(Game.class, "gameEnd");
		
		for (Robot i : robots) {
			i.getDistance();
		}
		
		Log.exitFunction();
	}
	
	/**
	 * A j�t�kban l�v� robotok lek�rdez�s�t szolg�l� met�dus
	 * @return a j�t�kban l�v�robotok
	 */
	public List<Robot> getRobots(){
		Log.enterFunction(Game.class, "getRobots");
				
		Log.exitFunction("List<Robots>");
		return null;
	}
	
}
