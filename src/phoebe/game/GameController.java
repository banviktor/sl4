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
	 * Az oszt�ly konstruktora
	 */
	public GameController() {
		game =  null;
		running = false;
	}
	
	
	/** 
	 * L�trehoz egy �j j�t�kot ha nincs �pp fut�s alatt egy m�sik, �s elmenti hogy fut
	 */
	public void newGame() {
		// Ha nem fut �pp j�t�k, l�trehoz egy �jat
		if (!running) {
			Map map = new Map("map.xml");
			
//J�T�KOSSZ�M BEK�R�S
int players = -2;
			
			if (players < 2) { players = 2; }
			else if (players > 5) { players = 5; }
			game = new Game(players, map, this);
			running = true;
		}
		
	}
	
	/**
	 * Be�ll�tja, hogy nincs fut� j�t�k
	 */
	public void gameEnded() {
		running = false;
		game = null;
	}
	
	/**
	 * Visszaadja hogy van-e �ppen fut� j�t�k
	 * @return
	 */
	public boolean isRunning() {
		return running;
	}
	
	
	/**
	 * Visszaadja a game-ben t�rolt robotvez�rl�t
	 * @return Robotvez�rl�
	 */
	public RobotController getRobotController() {
		return game.getRobotController();
	}
	
	
	/**
	 * Visszaadja a game-ben t�rolt p�ly�t
	 * @return P�lya
	 */
	public Map getMap() {
		return game.getMap();
	}
	
	
	/**
	 * Visszaadja a t�rolt j�t�kot.
	 * @return J�t�k
	 */
	public Game getGame() {
		return game;
	}
	
}
