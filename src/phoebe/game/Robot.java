package phoebe.game;

import phoebe.Log;
import phoebe.basic.Vector;

/**
 * Az általános robotokat megvalósító absztrakt osztály. Tárolja a robot helyét
 * és elérhetővé teszi az általános funkcióit.
 */
public abstract class Robot {
	protected Vector position;
	protected double radius = 0;
	
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
	public boolean isAt(Vector p){
		return (position.distance(p) < radius);
	}
	
	/**
	 * Megvizsgálja, hogy a robot és a paramtéterként kapott másik robot átfedi-e egymást.
	 * @param r A másik robot
	 * @return Átfedik-e egymást
	 */
	public boolean overlaps(Robot r){
		return (position.distance(r.position) < (radius + r.radius));
	}
	
	
	/**
	 * Absztrakt metódus, melyben a robot létrehoz egy olajfoltot az aktuális pozícióján
	 * @return a létrehozott olajfolt
	 */
	public abstract Oil createOil();
	
}
