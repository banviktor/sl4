package phoebe.game;

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
