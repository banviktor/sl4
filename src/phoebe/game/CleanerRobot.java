package phoebe.game;

import phoebe.basic.Vector;

/**
 * A takarítórobotokat megvalósító osztály, megvalósítja azok mozgását,
 * takarítását és ütközését
 */
public class CleanerRobot extends Robot {

	private Map map;
	private Game game;
	
	/**
	 * Az osztály konstruktora
	 * @param p a robot pozíciója
	 * @param m	az aktuális térkép, melyen a robot szerepel
	 * @param g az aktuális játék, melyben a robot szerepel
	 */
	public CleanerRobot(Vector p, Map m, Game g) {
		super(p);
		map = m;
		game = g;
	}

	/**
	 * A metódus eldönti, hogy a 0.3 egységnyi sugarú robot az adott pozíción található-e
	 * @param p a megadott pozíció
	 * @return	logikai érték, mely megadja, hogy a robot az adott pozíción van-e
	 */
	public boolean isAt(Vector p) {
		return (position.distance(p) < 0.3);
	}

	/**
	 * Metódus, melyben a takarítórobot létrehoz egy olajfoltot az aktuális pozícióján
	 * @return	a létrehozott olajfolt
	 */
	public Oil createOil() {
		return new Oil(position);
	}
	
	/**
	 * A takarítórobot egy körbeli cselekvéseit megvalósító metódus
	 */
	public void clear() {
		
	}
}
