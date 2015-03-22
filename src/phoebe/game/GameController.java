package phoebe.game;

import phoebe.Log;
import phoebe.UserInput;

/** 
 * A j�t�k l�trehoz�s��rt �s t�rol�s��rt felel�s oszt�ly
 */
public class GameController {
	
	private Game game;
	private boolean running;

	/** 
	 * L�trehoz egy �j j�t�kot ha nincs �pp fut�s alatt egy m�sik, �s elmenti hogy fut
	 */
	public void newGame() {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(GameController.class, "newGame");
		
		// Ha nem fut �pp j�t�k, l�trehoz egy �jat
		// Szkeleton megval�s�t�s miatt megh�vja az isRunningot, mert ott teszi fel a k�rd�st
		if (!isRunning()) {
			Map map = new Map("map.xml");
			
			// Szkeleton megval�s�t�s, bek�rj�k a j�t�kosok sz�m�t
			int players = UserInput.getInt("H�ny j�t�kossal?", 5);
			// A hat�rokon k�v�les� input eset�n a m�dos�t�s
			if (players < 2) { players = 2; }
			else if (players > 5) { players = 5; }
			
			// Az �j j�t�k l�trehoz�sa a megfelel� adatokkal
			game = new Game(players, map, this);
		}
		
		//Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * Be�ll�tja, hogy nincs fut� j�t�k
	 */
	public void gameEnded() {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(GameController.class, "gameEnded");
		
		
		//Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * Visszaadja hogy van-e �ppen fut� j�t�k
	 * @return
	 */
	public boolean isRunning() {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(GameController.class, "isRunning");
		
		// Szkeleton megval�s�t�s, a felhaszn�l�t�l megk�rdezz�k, hogy van-e fut� j�t�k
		running = UserInput.getBoolean("Van fut� j�t�k?", false);
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(String.valueOf(running));		
		return running;
	}
	
	/**
	 * Szkeleton megval�s�t�s: csak itt lesz sz�ks�gi ilyen lek�rdez�sre
	 * Visszaadja a j�t�khoz tartoz� RobotControllert
	 * @return a RobotController
	 */
	public RobotController getRobotController(){
		return game.getRobotController();
	}	
	
	/**
	 * Szkeleton megval�s�t�s: csak itt lesz sz�ks�gi ilyen lek�rdez�sre
	 * Visszaadja a j�t�khoz tartoz� Mapet
	 * @return a Map
	 */
	public Map getMap(){
		return game.getMap();
	}	
}
