package phoebe.game;

import phoebe.Log;
import phoebe.basic.Vector;

/**
 * Az általános robotokat megvalósító absztrakt osztály. Tárolja a robot helyét
 * és elérhetõvé teszi az általános funkcióit.
 */
public abstract class Robot {

	protected Vector position;
	
	public Robot(Vector p) {
		this.position = p;
	}
	
	/**
	 * Visszatér a robot aktuális pozíciójával
	 * @return a robot helyvektorával tér vissza
	 */
	public Vector getPosition(){
		return position;
	}
	
	/**
	 * Absztrakt metódus annak eldöntésére, hogy a robot az adott pozíción található-e
	 * @param p az adott pozíció
	 * @return logikai érték, mely megadja, hogy a robot az adott pozíción van-e
	 */
	public abstract boolean isAt(Vector p);
	
	
	/**
	 * Absztrakt metódus, melyben a robot létrehoz egy olajfoltot az aktuális pozícióján
	 * @return a létrehozott olajfolt
	 */
	public abstract Oil createOil();
	
}
