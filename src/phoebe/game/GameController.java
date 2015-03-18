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
			Map map = new Map("xtx.txt");
			game = new Game(5, map, this);
		}
		
		//F�ggv�nyb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * Be�ll�tja, hogy nincs fut� j�t�k
	 */
	public void gameEnded() {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(GameController.class, "gameEnded");
		
		
		//F�ggv�nyb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * Visszaadja hogy van-e �ppen fut� j�t�k
	 * @return
	 */
	public boolean isRunning() {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(GameController.class, "isRunning");
		
		running = UserInput.getBoolean("Van fut� j�t�k?", false);
		
		//F�ggv�nyb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(String.valueOf(running));		
		return running;
	}
	
}
