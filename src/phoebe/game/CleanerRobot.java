package phoebe.game;

import java.util.List;

import phoebe.UserIO;
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
		this.radius = 0.3;
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
		
		// Ha nincs folt, egy helyben marad.
		if (nearest == null)
			return;
		
		if (nearest.getPosition().distance(position) > 1) {
			//Ha még nem vagyunk rajta, ugrunk felé egységnyit
			position = position.add(new Vector(position,nearest.getPosition()).normalized());
			
			// Megnézzük, hogy ütközik-e másik robottal az új pozíción
			boolean newJump = true;
			while (newJump) {
				newJump = false;
				for(Robot r : game.getRobots()){
					if(r.overlaps(this) && !r.equals(this)){
						// Ha ütközés van
						Vector modification = UserIO.getVector("Takarítórobot ütközött " + position.toString() + ", merre próbáljon ugrani?", new Vector(Math.random(), Math.random()));						
						Vector newPosition = position.add(modification.normalized());
						
						//Ha nem manuálisan lett beállítva az új irány, akkor megnézzük, hogy kiment-e a mapról
						if(!UserIO.isRandom()){
							while(newPosition.getX() < 0 | newPosition.getX() > Map.size | newPosition.getY() < 0 | newPosition.getY() > Map.size){
								newPosition = position.add((new Vector(Math.random(), Math.random())).normalized());
							}
						}
						
						newJump = true;
						break;
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
	
	public String toString(){
		return "Takarítórobot: " + position.getX() + ", " + position.getY();
	}
		
}
