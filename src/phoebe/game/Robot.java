package phoebe.game;

import phoebe.Log;
import phoebe.basic.Vector;

public class Robot {

	protected Vector position;
	
	public Robot(Vector p) {
		this.position = p;
	}
	
	/**
	 * Visszat�r a robot aktu�lis poz�ci�j�val
	 * @return a robot helyvektor�val t�r vissza
	 */
	public Vector getPosition(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Robot.class, "getPosition");
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(position);
		return position;
	}
}
