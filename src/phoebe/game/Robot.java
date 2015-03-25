package phoebe.game;

import phoebe.Log;
import phoebe.basic.Vector;

public class Robot {

	protected Vector position;
	
	public Robot(Vector p) {
		this.position = p;
	}
	
	/**
	 * Visszatér a robot aktuális pozíciójával
	 * @return a robot helyvektorával tér vissza
	 */
	public Vector getPosition(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Robot.class, "getPosition");
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(position);
		return position;
	}
}
