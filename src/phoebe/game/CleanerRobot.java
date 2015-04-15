package phoebe.game;

import java.util.List;


import phoebe.basic.Vector;

/**
 * A takarítórobotokat megvalósító osztály, megvalósítja azok mozgását,
 * takarítását és ütközését
 */
public class CleanerRobot extends Robot {

	private Map map;
	private Game game;
	
	public final double cleanerRadius = 0.3;
	
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
	@Override
	public boolean isAt(Vector p) {
		return (position.distance(p) < 0.3);
	}

	/**
	 * Metódus, melyben a takarítórobot létrehoz egy olajfoltot az aktuális pozícióján
	 * @return	a létrehozott olajfolt
	 */
	@Override
	public Oil createOil() {
		return new Oil(position);
	}
	
	/**
	 * A takarítórobot egy körbeli cselekvéseit megvalósító metódus
	 */
	public void clear() {
		//Megnézzük, hol a legközelebbi folt, ami felé haladhatunk
		Smudge nearest = map.getNearestSmudgeTo(position);
		
		if (nearest.getPosition().distance(position) > 1) {
			//Ha még nem vagyunk rajta, ugrunk felé egységnyit
			position = position .add(new Vector(position,nearest.getPosition()).normalized());
			
			//És ellenőrizzük ugrottunk-e valakire
			boolean newJump = true;
			while (newJump) {
				newJump = false;
				for (double i = 0; i<360; i+=45) {
					double radians = Math.toRadians(i);
					//A robot szélein 8 pontra megnézzük, ütközött-e valakivel
					if (game.isRobotAt(position.add(new Vector(cleanerRadius*Math.cos(radians), cleanerRadius*Math.sin(radians))))) {
						//Ha ugrottunk, egy véletlen irányba elugrunk, és ott újra ellenőrizzük majd
						position = position .add(new Vector(Math.random(),Math.random()));
						newJump = true;
					}
				}
			}
		}
			
		//Ezután takarítunk minen foltot, amin vagyunk
		List<Smudge> smudges = map.getSmudgesAt(position);
		for (Smudge s : smudges) {
			if(s.reduceThickness() == 0) {
				map.deleteSmudge(s);
			}			
		}
		
	}
		
		
}
