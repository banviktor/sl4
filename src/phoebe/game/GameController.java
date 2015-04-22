package phoebe.game;

/** 
 * A játék létrehozásáért és tárolásáért felelős osztály
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
	public void newGame(int players) {
		// Ha nem fut épp játék, létrehoz egy újat
		if (!running) {
			Map map = new Map("map.xml");
			
			if (players < 2) { players = 2; }
			else if (players > 5) { players = 5; }
			game = new Game(players, map, this);
			running = true;
			//UserIO.println("Új játék elkezdve " + players + " játékossal.");
			//UserIO.println("Ez a(z) 1. kör.");
			//UserIO.println("A Piros Robot következik.");
		}
		else {
			//UserIO.println("Folyamatban van egy másik játék, nem lehet újat kezdeni.");
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
	 * Visszaadja a game-ben tárolt robotvezérlőt
	 * @return Robotvezérlő
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
