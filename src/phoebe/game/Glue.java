package phoebe.game;

import phoebe.Log;
import phoebe.UserIO;
import phoebe.basic.Vector;

public class Glue extends Smudge {
	
	/**
	 * Konstruktor az olajfolt pozíciójának megadásával
	 * @param p pozíció
	 */
	public Glue(Vector p) {
		super(p);
		
		// A ragacsfoltnak 4 az élettartama szemben az ősosztályban alapértelmezett 6-al
		duration = 4;
	}
	
	
	/**
	 * A folt hatását valósítja meg, az átadott robotnak felezi a sebességét
	 * @param r a robot, aminek felezi a sebességét
	 */
	@Override
	public int action(PlayerRobot r) {
		r.halveSpeed();

		// A ragacsfoltnak rálépéskor csökken az élettartama
		return --duration;
	}


	/**
	 * A ragacsfoltra nincs hatással a körváltás
	 * @return a folt élettartama
	 */
	@Override
	public int nextRound() {
		return duration;
	}
	
	
	@Override
	public String toString() {
		return "Ragacs: " + position;
	}	

}
