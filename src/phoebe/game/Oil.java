package phoebe.game;

import phoebe.Log;
import phoebe.UserInput;
import phoebe.basic.Vector;

public class Oil extends Smudge {
	
	/**
	 * Konstruktor az olajfolt pozíciójának megadásával
	 * @param p pozíció
	 */
	public Oil(Vector p) {
		super(p);
		}

	
	/**
	 * A folt hatását valósítja meg, az átadott robotra kikapcsolja a sebességváltoztatás
	 * lehetőségét
	 * @param r a robot, aminek kikapcsolja a sebességváltoztatását
	 */
	@Override
	public int action(PlayerRobot r) {
		r.disableSpeedModification();
		
		// Az olajfoltnak rálépéskor nem csökken az élettartama
		return duration;
	}

	
	/**
	 * Az olajfolt az új körben eszít egyet az élettartamából
	 * @return a folt új élettartama
	 */
	@Override
	public int nextRound() {
		return duration--;
	}
	

	@Override
	public String toString() {
		return "Olaj: " + position;
	}
		
}
