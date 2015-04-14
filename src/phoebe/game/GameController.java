package phoebe.game;

import phoebe.Log;
import phoebe.UserInput;

/** 
 * A játék létrehozásáért és tárolásáért felelõs osztály
 */
public class GameController {
	
	private Game game;
	private boolean running;

	
	/**
	 * Az osztály konstruktora
	 */
	public GameController() {
		game =  null;
		running = false;
	}
	
	
	/** 
	 * Létrehoz egy új játékot ha nincs épp futás alatt egy másik, és elmenti hogy fut
	 */
	public void newGame() {
		// Ha nem fut épp játék, létrehoz egy újat
		if (!running) {
			Map map = new Map("map.xml");
			
//JÁTÉKOSSZÁM BEKÉRÉS
int players = -2;
			
			if (players < 2) { players = 2; }
			else if (players > 5) { players = 5; }
			game = new Game(players, map, this);
			running = true;
		}
		
	}
	
	/**
	 * Beállítja, hogy nincs futó játék
	 */
	public void gameEnded() {
		running = false;
		game = null;
	}
	
	/**
	 * Visszaadja hogy van-e éppen futó játék
	 * @return
	 */
	public boolean isRunning() {
		return running;
	}
	
	
	/**
	 * Visszaadja a game-ben tárolt robotvezérlõt
	 * @return Robotvezérlõ
	 */
	public RobotController getRobotController() {
		return game.getRobotController();
	}
	
	
	/**
	 * Visszaadja a game-ben tárolt pályát
	 * @return Pálya
	 */
	public Map getMap() {
		return game.getMap();
	}
	
	
	/**
	 * Visszaadja a tárolt játékot.
	 * @return Játék
	 */
	public Game getGame() {
		return game;
	}
	
}
